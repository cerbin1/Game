package app.view.util;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;

public class CompatibleImageFactory implements ImageFactory {
    private GraphicsConfiguration configuration;

    public CompatibleImageFactory() {
        GraphicsEnvironment ge = getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        configuration = gs.getDefaultConfiguration();
    }

    @Override
    public BufferedImage create(int width, int height) {
        return configuration.createCompatibleImage(width, height);
    }
}
