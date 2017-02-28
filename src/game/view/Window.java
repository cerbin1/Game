package game.view;

import game.Load;
import game.view.fx.Transition;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import static java.awt.Color.white;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
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
        BufferedImage cardImage = Load.image("images/card.png");

        final int cardWidth = cardImage.getWidth();
        final int cardHeight = cardImage.getHeight();

        Graphics2D cardGraphics = cardImage.createGraphics();
        cardGraphics.setStroke(new BasicStroke(10));
        cardGraphics.setColor(white);
        cardGraphics.draw(new RoundRectangle2D.Float(
                10, 10, cardWidth - 20, cardHeight - 20,
                40, 40
        ));

        window.frame.setVisible(true);
        Graphics graphics = window.frame.getGraphics();

        BufferedImage backBuffer = new BufferedImage(1920, 1090, TYPE_INT_ARGB);
        Graphics2D canvas = backBuffer.createGraphics();

        double x = 0;
        while (true) {
            x = (x + 0.01) % 4;
            canvas.drawImage(image, 0, 0, null);

            double scaleX = 1.0 - Transition.cosineTransition(x);

            AffineTransform at = new AffineTransform();
            at.translate(350 - scaleX * cardWidth / 2, 100);
            at.scale(scaleX, 1.0);
            canvas.setTransform(at);

            canvas.drawImage(cardImage, 0, 0, null);
            canvas.setTransform(new AffineTransform());

            graphics.drawImage(backBuffer, 0, 0, null);
        }
    }
}
