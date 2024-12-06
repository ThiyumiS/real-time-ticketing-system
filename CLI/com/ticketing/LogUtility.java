package com.ticketing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtility {
    public static void logTransaction(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log file.");
        }
    }
}