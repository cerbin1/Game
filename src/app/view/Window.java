package app.view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class Window {
    private final JFrame frame = new JFrame();

    Window() {
        frame.setResizable(false);
        frame.setTitle("Game");
        frame.setSize(1920, 1080);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void show() {
        frame.setVisible(true);
    }

    Graphics getGraphics() {
        return frame.getGraphics();
    }
}
