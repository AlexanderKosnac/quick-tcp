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

    public static byte[] getHexStringAsByteArray(String hex) {
        hex = hex
            .replaceAll("(?i)0x", "")
            .replaceAll("\\s+", "")
            .toUpperCase();

        int len = hex.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have even length.");
        }
        if (!hex.matches("[0-9a-fA-F]*")) {
            throw new IllegalArgumentException("String contains non-hex characters.");
        }

        byte[] result = new byte[len/2];
        for (int i = 0; i < len; i += 2) {
            result[i/2] = (byte)(
                (Character.digit(hex.charAt(i), 16) << 4) +
                Character.digit(hex.charAt(i + 1), 16));
        }
        return result;
    } 
}
