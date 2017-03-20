package app.view;

import app.view.util.Optimization;

import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.RenderingHints.*;

public class BufferWindow extends Window {
    private BufferedImage backBuffer;
    private Graphics2D canvas;

    private Graphics windowGraphics;

    public BufferWindow() {
        backBuffer = Optimization.createCompatibleImage(1920, 1080);
        canvas = backBuffer.createGraphics();

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
