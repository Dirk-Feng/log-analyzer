package com.dirk.loganalyzer.service;

import com.dirk.loganalyzer.model.LogEntry;
import com.dirk.loganalyzer.parser.LogParser;
import com.dirk.loganalyzer.model.LogReadResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogReader {
    // LogReader只管读
    // Logparser管解析
    // 如果要换不同解析规则就方便了
    private final LogParser parser;

    public LogReader(LogParser parser)
    {
        this.parser = parser;
    }

    public LogReadResult readFromFile(String filePath)
    {
        List<LogEntry> entries = new ArrayList<>();
        int successCount = 0;
        int failedCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            int lineNumber = 0;

            while((line = reader.readLine()) != null)
            {
                lineNumber ++;

                try{
                    LogEntry entry = parser.parse(line);
                    entries.add(entry);
                    successCount ++;
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("skip invalid line "+ lineNumber + ":" + line);
                    failedCount ++;
                }
            }
        } catch(IOException e)
        {
            throw new RuntimeException("Failed to read log file: " + filePath, e);
        }
        return new LogReadResult(entries, successCount, failedCount);
    }
}
