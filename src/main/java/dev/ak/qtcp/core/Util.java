package dev.ak.qtcp.core;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

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

    public static Image loadSvgAsImage(String resourcePath, float width, float height) throws URISyntaxException, IOException, TranscoderException {
        try (InputStream in = Util.class.getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IOException("Failed to get resource: " + resourcePath);
            }
            return loadSvgAsImage(in, width, height);
        }
    }

    private static Image loadSvgAsImage(InputStream in, float width, float height) throws IOException, TranscoderException {
        class BufferedImageTranscoder extends ImageTranscoder {
            private BufferedImage image;

            @Override
            public BufferedImage createImage(int w, int h) {
                return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            }

            @Override
            public void writeImage(BufferedImage img, TranscoderOutput output) {
                this.image = img;
            }

            public BufferedImage getBufferedImage() {
                return image;
            }
        }

        BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
        transcoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, width);
        transcoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, height);

        TranscoderInput input = new TranscoderInput(in);
        transcoder.transcode(input, null);

        return transcoder.getBufferedImage();
    }
}
