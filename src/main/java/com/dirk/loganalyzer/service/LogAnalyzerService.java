package com.dirk.loganalyzer.service;

import com.dirk.loganalyzer.model.LogEntry;

import java.util.Comparator;
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

    public List<LogEntry> sortByTime(List<LogEntry> entries)
    {
        // return entries.stream()
        // .sorted((e1, e2) -> e1.getTimestamp().compareTo(e2.getTimestamp()))
        // .toList();
        
        // 直接用sorted的重载方法，传入一个Comparator
        return entries.stream()
        .sorted(Comparator.comparing(LogEntry::getTimestamp))
        .toList();
        // .sorted(Comparator.comparing(Class::getField)) 这种方式更简洁，直接指定要比较的字段
    }

    public List<LogEntry> filterByLevel(List<LogEntry> entries, String level)
    {
        return entries.stream()
        .filter(entry -> entry.getLevel().equals(level))
        .toList();
    }
}
