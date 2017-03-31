package app.view.util;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class JavaImageFactory implements ImageFactory {
    @Override
    public BufferedImage create(int width, int height) {
        return new BufferedImage(width, height, TYPE_INT_RGB);
    }
}
