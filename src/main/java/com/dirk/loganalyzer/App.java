package com.dirk.loganalyzer;

import com.dirk.loganalyzer.service.LogReader;
import com.dirk.loganalyzer.parser.LogParser;
import com.dirk.loganalyzer.model.LogReadResult;
import com.dirk.loganalyzer.service.LogAnalyzerService;

import java.util.Map;

public class App {

    public static void main(String[] args) {
        LogParser parser = new LogParser();
        LogReader reader = new LogReader(parser);
        LogAnalyzerService analyzerService = new LogAnalyzerService();

        LogReadResult result = reader.readFromFile("logs/sample.log");
        
        System.out.println("success: " + result.getSuccessCount());
        System.out.println("failed: " + result.getFailedCount());
        Map<String, Long> stats = analyzerService.countALlLevels(result.getEntries());
        System.out.println("Log level counts: ");
        for (Map.Entry<String, Long> entry : stats.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }       

    }
}

/*
mvn clean compile exec:java "-Dexec.mainClass=com.dirk.loganalyzer.App"
*/