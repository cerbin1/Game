package app;

import app.game.Updatable;
import app.game.card.Card;
import app.game.card.CardFactory;
import app.game.token.Token;
import app.view.SubsequentCardDealer;
import app.view.Window;
import app.view.render.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        window.addMouseListener(new GameMouseAdapter());
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
        CardFactory cardFactory = new CardFactory();

        List<Card> cards = new ArrayList<>();
        List<CardVO> cardVOs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Card card = cardFactory.createCheapCard();
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
        updatables.add(tokenVO);
        updatables.add(tokenVO2);
        updatables.add(tokenVO3);
        updatables.add(versatileVO);

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

    private Optional<Renderer> getRendererOnPoint(Point point) {
        return renderers
                .stream()
                .filter(Renderer::isHoverable)
                .filter(renderer -> renderer.getViewObject().getOutline().contains(point))
                .reduce((a, b) -> b);
    }

    public class GameMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Optional<Renderer> optional = getRendererOnPoint(e.getPoint());
            optional.ifPresent(renderer -> renderer.getViewObject().setRotation(slightRotation() * 10));
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            renderers.forEach(r -> r.getViewObject().setHover(false));
            Optional<Renderer> optional = getRendererOnPoint(e.getPoint());

            if (optional.isPresent()) {
                optional.get().getViewObject().setHover(true);
                window.setHandCursor();
            } else {
                window.setDefaultCursor();
            }
        }
    }
}
