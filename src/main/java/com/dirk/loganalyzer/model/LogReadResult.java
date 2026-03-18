package com.dirk.loganalyzer.model;

import java.util.List;

public class LogReadResult {
    private final List<LogEntry> entries;
    private final int successCount;
    private final int failedCount;

    public LogReadResult(List<LogEntry> entries, int successCount, int failedCount)
    {
        this.entries = entries;
        this.successCount = successCount;
        this.failedCount = failedCount;
    }

    public List<LogEntry> getEntries(){
        return entries;
    }

    public int getSuccessCount(){
        return successCount;
    }

    public int getFailedCount(){
        return failedCount;
    }
}
