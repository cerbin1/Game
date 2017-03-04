package app;

import app.game.Updatable;
import app.game.card.Card;
import app.game.card.CardBuilder;
import app.game.token.Token;
import app.view.Window;
import app.view.render.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static app.game.token.TokenColor.Green;
import static app.view.render.ViewObject.slightRotation;
import static java.awt.RenderingHints.*;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class GameWindow implements Updatable {
    private final Window window = new Window();

    private List<Updatable> updatables = new ArrayList<>();
    private List<Renderer> renderers = new ArrayList<>();

    private Graphics windowGraphics;
    private BufferedImage backBuffer;
    private Graphics2D canvas;

    GameWindow() {
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
        CardBuilder cardBuilder = new CardBuilder();

        List<Card> cards = new ArrayList<>();
        List<CardVO> cardVOs = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Card card = cardBuilder.createCheapCard();
            cards.add(card);
            CardVO cardVO = new CardVO(card, 300, 300);
            cardVO.setRotation(slightRotation());
            cardVOs.add(cardVO);
        }

        TokenVO tokenVO = new TokenVO(1000, 500, new Token(Green));
        TokenVO tokenVO2 = new TokenVO(1010, 520, new Token(Green));
        TokenVO tokenVO3 = new TokenVO(990, 540, new Token(Green));
        TokenVO versatileVO = new TokenVO(1100, 550, new Token(null));

        cardVOs.forEach(vo -> updatables.add(vo));
        updatables.add(tokenVO);

        renderers.add(new BackgroundRenderer());
        cardVOs.forEach(vo -> renderers.add(new CardRenderer(vo)));
        renderers.add(new TokenRenderer(tokenVO));
        renderers.add(new TokenRenderer(tokenVO2));
        renderers.add(new TokenRenderer(tokenVO3));
        renderers.add(new TokenRenderer(versatileVO));

        for (int i = 0; i < 4; i++) {
            CardVO lastCard = cardVOs.get(cardVOs.size() - 1 - i);
            lastCard.moveTo(670 + i * 238, 300, 2.0);
            lastCard.setFlipped(true);
        }
    }

    public void update(double secondsElapsed) {
        updatables.forEach(updatable -> updatable.update(secondsElapsed));
    }

    public void render() {
        renderers.forEach(renderer -> renderer.renderOn(canvas));
        windowGraphics.drawImage(backBuffer, 0, 0, null);
    }

    void show() {
        window.show();
        windowGraphics = window.getGraphics();
    }
}
