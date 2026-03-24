package com.dirk.loganalyzer;

import com.dirk.loganalyzer.service.LogReader;
import com.dirk.loganalyzer.parser.LogParser;
import com.dirk.loganalyzer.model.LogReadResult;
import com.dirk.loganalyzer.service.LogAnalyzerService;

import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java App <log_file> <command>");
            System.out.println("Commands: stats | hourly | topError");
            return;
        }

        String filePath = args[0];
        String command = args[1];

        LogParser parser = new LogParser();
        LogReader reader = new LogReader(parser);
        LogAnalyzerService analyzerService = new LogAnalyzerService();

        LogReadResult result = reader.readFromFile(filePath);

        switch (command) {
            case "stats":
                printLevelStats(analyzerService, result);
                break;
            case "hourly":
                printHourlyStats(analyzerService, result);
                break;
            case "topError":
                printTopErrors(analyzerService, result);
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private static void printLevelStats(LogAnalyzerService service, LogReadResult result) {
        Map<String, Long> stats = service.countAllLevels(result.getEntries());
        System.out.println("Level statistics:");
        for (Map.Entry<String, Long> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void printHourlyStats(LogAnalyzerService service, LogReadResult result) {
        Map<Integer, Long> stats = service.countLogsByHour(result.getEntries());
        System.out.println("Logs per hour:");
        for (Map.Entry<Integer, Long> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ":00 -> " + entry.getValue());
        }
    }

    private static void printTopErrors(LogAnalyzerService service, LogReadResult result) {
        List<Map.Entry<String, Long>> topErrors =
                service.topErrorMessages(result.getEntries(), 3);

        System.out.println("Top error messages:");
        for (Map.Entry<String, Long> entry : topErrors) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}