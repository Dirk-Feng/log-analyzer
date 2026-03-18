package com.dirk.loganalyzer;

import com.dirk.loganalyzer.model.LogEntry;
import com.dirk.loganalyzer.parser.LogParser;
import com.dirk.loganalyzer.service.LogReader;
import com.dirk.loganalyzer.model.LogReadResult;
import java.time.LocalDateTime;

import java.util.List;
public class App {

    public static void main(String[] args) {
        LogParser parser = new LogParser();
        LogReader reader = new LogReader(parser);
        LogReadResult result = reader.readFromFile("logs/sample.log");
        System.out.println("success: " + result.getSuccessCount());
        System.out.println("failed: " + result.getFailedCount());
        
        for (LogEntry entry: result.getEntries()) {
            System.out.println(entry);
        }    
    }
}

/*
mvn clean compile exec:java "-Dexec.mainClass=com.dirk.loganalyzer.App"
*/