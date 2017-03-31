package app.view.util;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;

public class CompatibleImageFactory implements ImageFactory {
    GraphicsConfiguration gc;

    public CompatibleImageFactory() {
        GraphicsEnvironment ge = getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        gc = gs.getDefaultConfiguration();
    }

    @Override
    public BufferedImage create(int width, int height) {
        return gc.createCompatibleImage(width, height);
    }
}
