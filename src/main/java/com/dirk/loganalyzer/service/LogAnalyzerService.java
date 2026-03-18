package com.dirk.loganalyzer.service;

import com.dirk.loganalyzer.model.LogEntry;

import java.util.List;

//业务服务层 service
public class LogAnalyzerService {
    public long countBylevel(List<LogEntry> entries, String level)
    {
        return entries.stream()
        .filter(entry -> entry.getLevel().equals(level))
        .count();
    }
}
