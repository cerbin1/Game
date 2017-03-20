package app.view.util;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;

public class Optimalization {
    public static BufferedImage createCompatibleImage(int width, int height) {
        GraphicsEnvironment ge = getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gs.getDefaultConfiguration();

        return gc.createCompatibleImage(width, height);
    }
}
