package com.dirk.loganalyzer;

import com.dirk.loganalyzer.service.LogReader;
import com.dirk.loganalyzer.parser.LogParser;
import com.dirk.loganalyzer.model.LogEntry;
import com.dirk.loganalyzer.model.LogReadResult;
import com.dirk.loganalyzer.service.LogAnalyzerService;

import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        LogParser parser = new LogParser();
        LogReader reader = new LogReader(parser);
        LogAnalyzerService analyzerService = new LogAnalyzerService();

        LogReadResult result = reader.readFromFile("logs/sample.log");
        
        System.out.println("success: " + result.getSuccessCount());
        System.out.println("failed: " + result.getFailedCount());
        
        System.out.println("Log level counts: ");
        Map<String, Long> stats = analyzerService.countALlLevels(result.getEntries());
        for (Map.Entry<String, Long> entry : stats.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }       
        
        System.out.println("Entries sorted by time:");
        List<LogEntry> sortedEntries = analyzerService.sortByTime(result.getEntries());
        sortedEntries.forEach(System.out::println);

        System.out.println("ERROR level entries:");
        List<LogEntry> errorEntries = analyzerService.filterByLevel(result.getEntries(), "ERROR");
        errorEntries.forEach(System.out::println);

        // use both sort and filter together
        System.out.println("INFO level entries sorted by time:");
        List<LogEntry> sortedInfoEntries = analyzerService.filterByLevel(result.getEntries(), "INFO");
        List<LogEntry> sortedEntries2 = analyzerService.sortByTime(sortedInfoEntries);
        sortedEntries2.forEach(System.out::println);
    }
}

/*
mvn clean compile exec:java "-Dexec.mainClass=com.dirk.loganalyzer.App"
*/