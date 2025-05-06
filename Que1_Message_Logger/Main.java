package Que1_Message_Logger;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();

        int threadCount = 100;
        int messagesPerThread = 100;
        Thread[] threads = new Thread[threadCount];

        String[] sampleMessages = {
                "User login successful",
                "Disk space running low",
                "Failed to save record to database",
                "Connection timeout occurred",
                "Scheduled task completed",
                "Unauthorized access attempt detected",
                "File uploaded successfully",
                "Memory usage is high",
                "Database query executed",
                "Critical system failure"
        };

        Random random = new Random();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < messagesPerThread; j++) {
                    String msg = sampleMessages[random.nextInt(sampleMessages.length)];
                    Logger.Level level = getLevelFromMessage(msg);
                    try {
                        threads[j].sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    logger.log(msg, level);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        logger.shutdown();
    }

    // Helper method to guess log level based on message content
    private static Logger.Level getLevelFromMessage(String message) {
        String lower = message.toLowerCase();
        if (lower.contains("fail") || lower.contains("unauthorized") || lower.contains("critical") || lower.contains("timeout")) {
            return Logger.Level.ERROR;
        } else if (lower.contains("low") || lower.contains("high") || lower.contains("memory") || lower.contains("warning")) {
            return Logger.Level.WARN;
        } else {
            return Logger.Level.INFO;
        }
    }
}

