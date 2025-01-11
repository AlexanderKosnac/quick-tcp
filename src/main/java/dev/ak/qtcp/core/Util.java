package dev.ak.qtcp.core;

public class Util {

    public static int getStringAsIntOrZero(String input) {
        return getStringAsIntOrDefault(input, 0);
    }

    public static int getStringAsIntOrDefault(String input, int defaultVal) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }
}
