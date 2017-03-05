package app.view;

import app.GameWindow;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Window {
    private final JFrame frame = new JFrame();

    public Window() {
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

    public Graphics getGraphics() {
        return frame.getGraphics();
    }
}
