package com.dirk.loganalyzer.parser;

import com.dirk.loganalyzer.model.LogEntry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogParser {
    // static 所有Logparser对象共享这一份格式器，不用每次都new
    // final 这个格式定义好好不需要改
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogEntry parse(String line)
    {
        // 2026-03-18 10:15:23 INFO User login success from mobile device
        String[] parts = line.split(" ", 4); // 最多分成四段 日期 时间 级别 剩下都作为message
        
        if (parts.length < 4)
            throw new IllegalArgumentException("invalid log format: " + line);
        LocalDateTime timestamp = LocalDateTime.parse(parts[0] + " " + parts[1], FORMATTER);
        //再按指定格式解析成 LocalDateTime
        String level = parts[2];
        String message = parts[3];

        return new LogEntry(timestamp, level, message);
    }
    
}
