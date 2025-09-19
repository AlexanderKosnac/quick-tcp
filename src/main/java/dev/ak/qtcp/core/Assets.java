package dev.ak.qtcp.core;

import java.awt.Image;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.batik.transcoder.TranscoderException;

public class Assets {

    private static final Map<Integer, Image> ICON_CACHE = new HashMap<>();

    static {
        try {
            int[] sizes = {16, 32, 48, 64, 96, 128, 256};
            for (int size : sizes) {
                ICON_CACHE.put(size, Util.loadSvgAsImage("/icon.svg", size, size));
            }
        } catch (URISyntaxException | IOException | TranscoderException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static Image getIcon(int size) {
        return ICON_CACHE.get(size);
    }

    public static Map<Integer, Image> getAllIconsByResolution() {
        return ICON_CACHE;
    }

    public static List<Image> getAllIcons() {
        return ICON_CACHE.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(Map.Entry::getValue)
            .toList();
    }
}
