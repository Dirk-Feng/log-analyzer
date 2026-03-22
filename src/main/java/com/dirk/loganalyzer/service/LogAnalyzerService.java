package com.dirk.loganalyzer.service;

import com.dirk.loganalyzer.model.LogEntry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//业务服务层 service
public class LogAnalyzerService {
    public long countBylevel(List<LogEntry> entries, String level)
    {
        return entries.stream()
        .filter(entry -> entry.getLevel().equals(level))
        .count();
    }

    // 比countBylevel更通用的方法，可以统计任意level的数量, 而且在一次stream就处理好了所有的level
    public Map<String, Long> countALlLevels(List<LogEntry> entries)
    {
        return entries.stream()
        .collect(Collectors.groupingBy(LogEntry::getLevel, Collectors.counting()));
        // 分组后概念上变成
        // INFO  -> [entry1, entry3, entry6]
        // ERROR -> [entry2, entry5]
        // WARN  -> [entry4]
        // ... -> ...
        // 然后统计每个level的数量
    }


}
