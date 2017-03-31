package app.presenter;

import app.model.Game;
import app.model.GameBuilder;
import app.model.Player;
import app.model.Updatable;
import app.model.card.Card;
import app.model.card.CardFactory;
import app.model.card.nobility.Nobility;
import app.model.token.Token;
import app.model.token.TokenColor;
import app.model.token.Tokens;
import app.model.util.Probability;
import app.view.BufferWindow;
import app.view.SubsequentCardDealer;
import app.view.render.Tableable;
import app.view.render.renderer.*;
import app.view.render.vo.*;
import app.view.util.FastClickMouseAdapter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.view.render.vo.ViewObject.slightRotation;
import static java.awt.RenderingHints.Entry;
import static java.util.Collections.shuffle;

public class GameWindow implements Updatable {
    private final static Probability probability = new Probability();

    private final BufferWindow window = new BufferWindow();

    private final List<Updatable> updatables = new ArrayList<>();
    private final List<Renderer> renderers = new ArrayList<>();

    private final PositionTable table = new PositionTable(1800, 600);

    private Game game;

    public GameWindow() {
        window.addMouseListener(new GameMouseAdapter());
        initializeGame();
    }

    private void initializeGame() {
        CardFactory cardFactory = new CardFactory();
        GameBuilder builder = new GameBuilder();

        Tokens tokens = new Tokens(7, 5);
        List<Player> players = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        List<Nobility> nobilities = new ArrayList<>();

        players.add(new Player());

        Nobility nobility = new Nobility(new Tokens(1, 2, 3, 4, 0), 3);
        NobilityVO nobilityVO = new NobilityVO(nobility, 1000, 1500);
        nobilities.add(nobility);

        ButtonVO buttonVO = new ButtonVO(1600, 1600);
        buttonVO.addClickListener(viewObject -> buttonClicked());

        List<TokenVO> tokenVOs = new ArrayList<>();
        for (Entry<TokenColor, Integer> entry : tokens.asMap().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                TokenVO tokenVO = new TokenVO(new Token(entry.getKey()), probability.nextInt(1700, 2100), probability.nextInt(100, 300));
                tokenVO.addClickListener(this::tableableClicked);
                tokenVOs.add(tokenVO);
            }
        }
        shuffle(tokenVOs);

        for (int i = 0; i < tokens.getVersatile(); i++) {
            TokenVO versatileVO = new TokenVO(new Token(null), probability.nextInt(2200, 2300), probability.nextInt(100, 300));
            versatileVO.addClickListener(this::tableableClicked);
            tokenVOs.add(versatileVO);
        }

        List<CardVO> cardVOs = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Card cheapCard = cardFactory.createCheapCard();
            CardVO vo = new CardVO(cheapCard, 300, 200);
            vo.addClickListener(this::tableableClicked);
            vo.setRotation(slightRotation());
            cards.add(cheapCard);
            cardVOs.add(vo);
        }

        for (int i = 0; i < 4; i++) {
            Card mediumCard = cardFactory.createMediumCard();
            CardVO vo = new CardVO(mediumCard, 300, 530);
            vo.addClickListener(this::tableableClicked);
            vo.setRotation(slightRotation());
            cards.add(mediumCard);
            cardVOs.add(vo);
        }

        for (int i = 0; i < 4; i++) {
            Card expensiveCard = cardFactory.createExpensiveCard();
            CardVO vo = new CardVO(expensiveCard, 300, 860);
            vo.addClickListener(this::tableableClicked);
            vo.setRotation(slightRotation());
            cards.add(expensiveCard);
            cardVOs.add(vo);
        }

        game = new Game(tokens, players, cards, nobilities);

        new SubsequentCardDealer(cardVOs, 4, i -> 1430 - i * 238).deal();

        FpsRenderer fpsRenderer = new FpsRenderer(3130, 80);
        TextNotificationRenderer textNotificationRenderer = new TextNotificationRenderer("Testing", 3, 2000, 1000);

        updatables.addAll(cardVOs);
        updatables.addAll(tokenVOs);
        updatables.add(nobilityVO);
        updatables.add(fpsRenderer);
        updatables.add(textNotificationRenderer);

        renderers.add(new BackgroundRenderer());

        cardVOs.forEach(vo -> renderers.add(new CardRenderer(vo)));
        tokenVOs.forEach(vo -> renderers.add(new TokenRenderer(vo)));
        renderers.add(new NobilityRenderer(nobilityVO));

        renderers.add(new ButtonRenderer(buttonVO));
        renderers.add(fpsRenderer);
        renderers.add(textNotificationRenderer);
    }

    private void tableableClicked(ViewObject viewObject) {
        Tableable tableable = (Tableable) viewObject;
        if (table.has(tableable)) {
            table.take(tableable);
        } else {
            table.put(tableable);
        }
    }

    private void buttonClicked() {
        table.gather();
    }

    @Override
    public void update(double secondsElapsed) {
        updatables.forEach(updatable -> updatable.update(secondsElapsed));
    }

    public void render() {
        renderers.forEach(renderer -> renderer.renderOn(window.getCanvas()));
        window.flip();
    }

    public void show() {
        window.show();
    }

    private Optional<Renderer> getRendererOnPoint(Point point) {
        return renderers
                .stream()
                .filter(Renderer::isHoverable)
                .filter(renderer -> renderer.getViewObject().getOutline().contains(point))
                .reduce((a, b) -> b);
    }

    public class GameMouseAdapter extends FastClickMouseAdapter {
        @Override
        public void mouseFastClicked(MouseEvent event) {
            getRendererOnPoint(event.getPoint()).ifPresent(this::clickedRenderer);
        }

        private void clickedRenderer(Renderer renderer) {
            renderer.getViewObject().triggerClicked();
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            renderers.forEach(renderer -> renderer.getViewObject().setHover(false));
            Optional<Renderer> optional = getRendererOnPoint(event.getPoint());

            if (optional.isPresent()) {
                optional.get().getViewObject().setHover(true);
                window.setHandCursor();
            } else {
                window.setDefaultCursor();
            }
        }
    }
}
