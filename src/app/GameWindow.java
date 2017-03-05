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
import java.util.function.Function;

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

        for (int i = 0; i < 5; i++) {
            Card card = cardBuilder.createCheapCard();
            cards.add(card);
            CardVO cardVO = new CardVO(card, 300, 200);
            cardVO.setRotation(slightRotation());
            cardVOs.add(cardVO);
        }

        new SubsequentCardDealer(cardVOs, 4, i -> 1430 - i * 238).deal();

        TokenVO tokenVO = new TokenVO(1800 - 30, 500, new Token(Green));
        TokenVO tokenVO2 = new TokenVO(1810 - 30, 520, new Token(Green));
        TokenVO tokenVO3 = new TokenVO(1790 - 30, 540, new Token(Green));
        TokenVO versatileVO = new TokenVO(1900 - 30, 550, new Token(null));

        cardVOs.forEach(vo -> updatables.add(vo));

        renderers.add(new BackgroundRenderer());
        cardVOs.forEach(vo -> renderers.add(new CardRenderer(vo)));
        renderers.add(new TokenRenderer(tokenVO));
        renderers.add(new TokenRenderer(tokenVO2));
        renderers.add(new TokenRenderer(tokenVO3));
        renderers.add(new TokenRenderer(versatileVO));
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

    private class SubsequentCardDealer {
        private final List<CardVO> cardVOs;
        private final Function<Integer, Integer> xValue;

        private int amount;
        private int current = 0;

        SubsequentCardDealer(List<CardVO> cardVOs, int amount, Function<Integer, Integer> xValue) {
            this.cardVOs = cardVOs;
            this.xValue = xValue;
            this.amount = amount;
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount lower than 0");
            }
        }

        void deal() {
            if (current < amount) {
                CardVO vo = cardVOs.get(cardVOs.size() - 1 - current);
                vo.moveX(xValue.apply(current), 2.0, this::deal);
                vo.setFlipped(true);
                current++;
            }
        }
    }
}
