package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Window {
    private final JFrame frame = new JFrame();

    private Window() {
        frame.setResizable(false);
        frame.setTitle("Game");
        frame.setSize(1920, 1080);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Window window = new Window();
        window.show();

        BufferedImage image = Load.image("images/background_1.png");
        window.frame.setVisible(true);
        Graphics graphics = window.frame.getGraphics();
        graphics.drawImage(image, 0, 0, null);
    }
}
