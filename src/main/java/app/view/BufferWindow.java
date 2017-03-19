package app.view;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.RenderingHints.*;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class BufferWindow extends Window {
    private final BufferedImage backBuffer = new BufferedImage(1920, 1080, TYPE_INT_ARGB);
    private final Graphics2D canvas = backBuffer.createGraphics();

    private Graphics windowGraphics;

    public BufferWindow() {
        canvas.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        canvas.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
    }

    @Override
    public void show() {
        super.show();
        windowGraphics = this.getGraphics();
    }

    public void flip() {
        windowGraphics.drawImage(backBuffer, 0, 0, null);
    }

    public Graphics2D getCanvas() {
        return canvas;
    }
}
