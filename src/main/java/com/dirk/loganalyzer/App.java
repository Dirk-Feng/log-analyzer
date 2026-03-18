package com.dirk.loganalyzer;

import com.dirk.loganalyzer.service.LogReader;
import com.dirk.loganalyzer.parser.LogParser;
import com.dirk.loganalyzer.model.LogReadResult;
import com.dirk.loganalyzer.service.LogAnalyzerService;

public class App {

    public static void main(String[] args) {
        LogParser parser = new LogParser();
        LogReader reader = new LogReader(parser);
        LogAnalyzerService analyzerService = new LogAnalyzerService();

        LogReadResult result = reader.readFromFile("logs/sample.log");
        
        System.out.println("success: " + result.getSuccessCount());
        System.out.println("failed: " + result.getFailedCount());
        long errorcount = analyzerService.countBylevel(result.getEntries(), "ERROR");
        long infocount = analyzerService.countBylevel(result.getEntries(), "INFO");

        System.out.println("ERROR count: " + errorcount);
        System.out.println("INFO count: " + infocount);
    }
}

/*
mvn clean compile exec:java "-Dexec.mainClass=com.dirk.loganalyzer.App"
*/