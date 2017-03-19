package app.view;

import app.presenter.GameWindow;

import javax.swing.*;
import java.awt.*;

import static java.awt.Cursor.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Window {
    private final JFrame frame = new JFrame();

    private final Cursor handCursor = getPredefinedCursor(HAND_CURSOR);
    private final Cursor defaultCursor = getPredefinedCursor(DEFAULT_CURSOR);

    Window() {
        frame.setResizable(false);
        frame.setTitle("Game");
        frame.setSize(1920, 1080);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addMouseListener(GameWindow.GameMouseAdapter listener) {
        frame.addMouseListener(listener);
        frame.addMouseMotionListener(listener);
    }

    public void show() {
        frame.setVisible(true);
    }

    Graphics getGraphics() {
        return frame.getGraphics();
    }

    public void setHandCursor() {
        frame.setCursor(handCursor);
    }

    public void setDefaultCursor() {
        frame.setCursor(defaultCursor);
    }
}
