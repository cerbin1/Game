package app.game.view;

import app.game.TokenColor;
import app.game.Updatable;
import app.game.card.Card;
import app.game.card.CheapCard;
import app.game.view.render.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.awt.RenderingHints.*;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameWindow implements Updatable {
    private final Window window = new Window();

    private List<Updatable> updatables = new ArrayList<>();
    private List<Renderer> renderers = new ArrayList<>();

    private Graphics windowGraphics;
    private BufferedImage backBuffer;
    private Graphics2D canvas;

    public GameWindow() {
        initializeBackBuffer();
        initializeGame();
    }

    private void initializeBackBuffer() {
        backBuffer = new BufferedImage(1920, 1080, TYPE_INT_ARGB);
        canvas = backBuffer.createGraphics();
        canvas.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        canvas.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
    }

    private void initializeGame() {
        ImageRepository imageRepository = new ImageRepository();

        Card card = new CheapCard();
        CardVO cardVO = new CardVO(card, 500, 300);
        CardRenderer cardRenderer = new CardRenderer(canvas, cardVO, imageRepository);

        TokenVO tokenVO = new TokenVO(1000, 500, TokenColor.Green);
        TokenRenderer tokenRenderer = new TokenRenderer(canvas, tokenVO, imageRepository);
        tokenVO.moveTo(500, 700);

        TokenVO versatileVO = new TokenVO(1100, 550, null);
        TokenRenderer versatileTokenRenderer = new VersatileTokenRenderer(canvas, versatileVO, imageRepository);

        updatables.add(cardVO);
        updatables.add(tokenVO);

        renderers.add(new BackgroundRenderer(imageRepository));
        renderers.add(cardRenderer);
        renderers.add(tokenRenderer);
        renderers.add(versatileTokenRenderer);
    }

    public void update(double secondsElapsed) {
        updatables.forEach(updatable -> updatable.update(secondsElapsed));
    }

    public void render() {
        renderers.forEach(renderer -> renderer.renderOn(canvas));
        windowGraphics.drawImage(backBuffer, 0, 0, null);
    }

    public void show() {
        window.show();
        windowGraphics = window.getGraphics();
    }
}
