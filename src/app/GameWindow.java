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

import static app.view.ImageRepository.imageRepository;
import static app.view.render.ViewObject.slightRotation;
import static java.awt.RenderingHints.Entry;

public class GameWindow implements Updatable {
    private final BufferWindow window = new BufferWindow();

    private List<Updatable> updatables = new ArrayList<>();
    private List<Renderer> renderers = new ArrayList<>();

    private Probability probability = new Probability();

    private Point previousPoint;
    private ViewObject previousVO;

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
        NobilityVO nobilityVO = new NobilityVO(new Nobility(new Tokens(1, 2, 3, 4, 0), 3), 1000, 1500);
        ButtonVO buttonVO = new ButtonVO(1600, 1600);

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
            Card card = cardFactory.createCheapCard();
            cards.add(card);
            CardVO vo = new CardVO(card, 300, 200);
            vo.setRotation(slightRotation());
            cardVOs.add(vo);
        }

        for (int i = 0; i < 4; i++) {
            Card card = cardFactory.createMediumCard();
            cards.add(card);
            CardVO vo = new CardVO(card, 300, 530);
            vo.setRotation(slightRotation());
            cardVOs.add(vo);
        }
        for (int i = 0; i < 4; i++) {
            Card card = cardFactory.createExpensiveCard();
            cards.add(card);
            CardVO vo = new CardVO(card, 300, 860);
            vo.setRotation(slightRotation());
            cardVOs.add(vo);
        }

        new SubsequentCardDealer(cardVOs, 4, i -> 1430 - i * 238).deal();

        cardVOs.forEach(vo -> updatables.add(vo));
        tokenVOs.forEach(vo -> updatables.add(vo));
        updatables.add(nobilityVO);

        renderers.add(new BackgroundRenderer());
        renderers.add(new NobilityRenderer(nobilityVO));
        cardVOs.forEach(vo -> renderers.add(new CardRenderer(vo)));
        tokenVOs.forEach(vo -> renderers.add(new TokenRenderer(vo)));

        renderers.add(new ButtonRenderer(buttonVO));
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
            ViewObject currentVO = renderer.getViewObject();
            if (currentVO == previousVO) {
                currentVO.moveToConstantSpeed(previousPoint.x, previousPoint.y, 1.0);
                previousVO = null;
                previousPoint = null;
            } else {
                if (previousVO != null) {
                    previousVO.moveToConstantSpeed(previousPoint.x, previousPoint.y, 1.0);
                }
                previousVO = currentVO;
                previousPoint = new Point(currentVO.getX(), currentVO.getY());
                currentVO.moveToConstantSpeed(1800, 600, 1.0);
            }
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
