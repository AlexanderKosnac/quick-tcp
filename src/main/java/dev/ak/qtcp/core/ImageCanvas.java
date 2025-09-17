package dev.ak.qtcp.core;

import java.awt.*;

public class ImageCanvas extends Canvas {

    private Image image;

    public ImageCanvas(Image img) {
        image = img;

        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            return new Dimension(image.getWidth(this), image.getHeight(this));
        }
        return new Dimension(400, 400);
    }
}