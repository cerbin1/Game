package app;

import app.game.Game;
import app.game.GameBuilder;
import app.game.Player;
import app.game.Updatable;
import app.game.card.Card;
import app.game.card.CardFactory;
import app.game.card.nobility.Nobility;
import app.game.token.Token;
import app.game.token.TokenColor;
import app.game.token.Tokens;
import app.util.Probability;
import app.view.BufferWindow;
import app.view.SubsequentCardDealer;
import app.view.render.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static app.view.render.ViewObject.slightRotation;
import static java.awt.RenderingHints.Entry;

public class GameWindow implements Updatable {
    private final BufferWindow window = new BufferWindow();

    private List<Updatable> updatables = new ArrayList<>();
    private List<Renderer> renderers = new ArrayList<>();

    private Probability probability = new Probability();

    GameWindow() {
        window.addMouseListener(new GameMouseAdapter());
        initializeGame();
    }

    private void initializeGame() {
        CardFactory cardFactory = new CardFactory();
        GameBuilder builder = new GameBuilder();
        Tokens tokens = new Tokens(7, 5);
        Player player = new Player();
        Game game = builder.set(tokens).add(player).create();
        List<Card> cards = new ArrayList<>();
        List<CardVO> cardVOs = new ArrayList<>();
        List<TokenVO> tokenVOs = new ArrayList<>();
        NobilityVO nobilityVO = new NobilityVO(new Nobility(new Tokens(), 3), 300, 800);

        for (Entry<TokenColor, Integer> entry : tokens.asMap().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                tokenVOs.add(new TokenVO(probability.nextInt(1700, 2100), probability.nextInt(100, 300), new Token(entry.getKey())));
            }
        }
        Collections.shuffle(tokenVOs);

        for (int i = 0; i < tokens.getVersatile(); i++) {
            tokenVOs.add(new TokenVO(probability.nextInt(2200, 2300), probability.nextInt(100, 300), new Token(null)));
        }

        for (int i = 0; i < 4; i++) {
            Card card1 = cardFactory.createCheapCard();
            cards.add(card1);
            CardVO cardVO1 = new CardVO(card1, 300, 200);
            cardVO1.setRotation(slightRotation());
            cardVOs.add(cardVO1);
        }

        for (int i = 0; i < 4; i++) {
            Card card2 = cardFactory.createMediumCard();
            cards.add(card2);
            CardVO cardVO2 = new CardVO(card2, 300, 530);
            cardVO2.setRotation(slightRotation());
            cardVOs.add(cardVO2);
        }
        for (int i = 0; i < 4; i++) {
            Card card3 = cardFactory.createExpensiveCard();
            cards.add(card3);
            CardVO cardVO3 = new CardVO(card3, 300, 860);
            cardVO3.setRotation(slightRotation());
            cardVOs.add(cardVO3);
        }

        new SubsequentCardDealer(cardVOs, 4, i -> 1430 - i * 238).deal();

        cardVOs.forEach(vo -> updatables.add(vo));
        tokenVOs.forEach(vo -> updatables.add(vo));
        updatables.add(nobilityVO);

        renderers.add(new BackgroundRenderer());
        renderers.add(new NobilityRenderer(nobilityVO));
        cardVOs.forEach(vo -> renderers.add(new CardRenderer(vo)));
        tokenVOs.forEach(vo -> renderers.add(new TokenRenderer(vo)));
    }

    @Override
    public void update(double secondsElapsed) {
        updatables.forEach(updatable -> updatable.update(secondsElapsed));
    }

    public void render() {
        renderers.forEach(renderer -> renderer.renderOn(window.getCanvas()));
        window.flip();
    }

    void show() {
        window.show();
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
            getRendererOnPoint(e.getPoint()).ifPresent(this::clickedRenderer);
        }

        private void clickedRenderer(Renderer renderer) {
            renderer.getViewObject().setRotation(slightRotation() * 10);
            renderer.getViewObject().moveTo(1400, 350, 3.0);
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
