package app.game.view;

import app.game.Tokens;
import app.game.cards.*;
import app.game.view.render.CardRenderer;
import app.game.view.render.CardVO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.RenderingHints.*;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.Thread.sleep;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class Window {
    private static boolean shouldStop = false;
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

    private Graphics getGraphics() {
        return frame.getGraphics();
    }

    public static void main(String[] args) {
        Window window = new Window();
        window.show();
        Graphics graphics = window.getGraphics();

        ImageRepository imageRepository = new ImageRepository();

        BufferedImage backBuffer = new BufferedImage(1920, 1090, TYPE_INT_ARGB);
        Graphics2D canvas = backBuffer.createGraphics();

        canvas.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        canvas.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);

        Card card = new CheapCard(new Tokens(2, 2, 1, 0, 0));
        CardVO cardVO = new CardVO(card, 500, 300);
        CardRenderer cardRenderer = new CardRenderer(cardVO, imageRepository);

        double previous = System.nanoTime();
        while (!shouldStop) {
            double current = System.nanoTime();
            double elapsed = current - previous;
            previous = current;
            cardVO.update(elapsed / 1.0e9);

            canvas.drawImage(imageRepository.background1, 0, 0, null);
            cardRenderer.performRenderOn(canvas);
            graphics.drawImage(backBuffer, 0, 0, null);

            try {
                sleep(2);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
