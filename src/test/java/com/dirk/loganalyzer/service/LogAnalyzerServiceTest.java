package com.dirk.loganalyzer.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.dirk.loganalyzer.model.LogEntry;

import static org.junit.jupiter.api.Assertions.*;

public class LogAnalyzerServiceTest {

    @Test
    public void testCountByLevel(){
        LogAnalyzerService service = new LogAnalyzerService();
        List<LogEntry> entries = List.of(
            new LogEntry(LocalDateTime.now(), "INFO", "msg1"),
            new LogEntry(LocalDateTime.now(), "ERROR", "msg2"),
            new LogEntry(LocalDateTime.now(), "ERROR", "msg3")
        );
        long errorCount = service.countBylevel(entries, "ERROR");
        assertEquals(errorCount, 2);
    }

    @Test
    public void testCountLogsByHour(){
        LogAnalyzerService service = new LogAnalyzerService();
        List<LogEntry> entries = List.of(
            new LogEntry(LocalDateTime.of(2026,3,18,8,0), "INFO", "a"),   
            new LogEntry(LocalDateTime.of(2026,3,18,8,10), "INFO", "b"),
            new LogEntry(LocalDateTime.of(2026,3,18,9,0), "ERROR", "c")
        );
        Map<Integer, Long> result = service.countLogsByHour(entries);
        assertEquals(2, result.get(8));
        assertEquals(1, result.get(9));
    }
}