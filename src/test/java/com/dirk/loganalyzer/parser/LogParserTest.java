package com.dirk.loganalyzer.parser;

import com.dirk.loganalyzer.model.LogEntry;
import com.dirk.loganalyzer.parser.LogParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogParserTest {

    @Test
    public void testParseValidLogLine() {
        LogParser parser = new LogParser();

        String line = "2026-03-18 08:30:00 INFO Application started";
        LogEntry entry = parser.parse(line);

        // I hope level is "INFO", else test failed
        assertEquals("INFO", entry.getLevel());
        assertEquals("Application started", entry.getMessage());
    }

    @Test
    public void testParseInvalidLogLine() {
        LogParser parser = new LogParser();

        String badLine = "bad data";
        // 我希望parser.parser会报异常 如果不报异常就失败
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(badLine);
        });
    }
}