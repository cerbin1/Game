package app.presenter;

import app.model.Game;
import app.model.Player;
import app.model.Updatable;
import app.model.card.Card;
import app.model.card.CardFactory;
import app.model.token.Token;
import app.model.token.TokenColor;
import app.model.token.TokensAmount;
import app.model.turn.Turn;
import app.model.util.Probability;
import app.view.SubsequentCardDealer;
import app.view.render.Tableable;
import app.view.render.renderer.*;
import app.view.render.vo.ButtonVO;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import app.view.render.vo.ViewObject;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.view.render.vo.ViewObject.slightRotation;
import static java.awt.RenderingHints.Entry;
import static java.util.Collections.shuffle;

public class GameWindow implements Updatable {
    private final static TokensAmount gameTokensAmount = new TokensAmount(7, 5);
    private final static Probability probability = new Probability();

    private final List<Updatable> updatables = new ArrayList<>();
    private final List<Renderer> renderers = new ArrayList<>();

    private final PositionTable table = new PositionTable(1800, 600);
    private TextNotificationRenderer textNotificationRenderer = new TextNotificationRenderer(200, 100);

    private static final CardFactory cardFactory = new CardFactory();

    private Game game;

    GameWindow() {
        initializeGame();
    }

    private void initializeGame() {
        List<Player> players = new ArrayList<>();
        players.add(new Player());

        Pair<List<Card>, List<CardVO>> cardsAndVOs = createCardAndVOs();
        List<Card> cards = cardsAndVOs.getKey();
        List<CardVO> cardVOs = cardsAndVOs.getValue();

        List<TokenVO> tokenVOs = createTokenVOs();

        new SubsequentCardDealer(cardVOs, 4, i -> 1430 - i * 238).deal();

        game = new Game(gameTokensAmount, players, cards, new ArrayList<>());


        ButtonVO cancelButtonVo = new ButtonVO("Cancel", 1600, 1600);
        cancelButtonVo.addClickListener(viewObject -> cancelButtonClicked());

        ButtonVO turnButtonVo = new ButtonVO("Ok", 2100, 1600);
        turnButtonVo.addClickListener(viewObject -> turnButtonClicked());

        updatables.addAll(cardVOs);
        updatables.addAll(tokenVOs);
        updatables.add(textNotificationRenderer);

        renderers.add(new BackgroundRenderer());
        renderers.add(new ButtonRenderer(cancelButtonVo));
        renderers.add(new ButtonRenderer(turnButtonVo));
        renderers.add(new PositionTableDebugRenderer(table));

        cardVOs.forEach(vo -> renderers.add(new CardRenderer(vo)));
        tokenVOs.forEach(vo -> renderers.add(new TokenRenderer(vo)));

        renderers.add(textNotificationRenderer);
    }

    private List<TokenVO> createTokenVOs() {
        List<TokenVO> tokenVOs = new ArrayList<>();

        for (Entry<TokenColor, Integer> entry : gameTokensAmount.asMap().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                TokenVO tokenVO = new TokenVO(new Token(entry.getKey()), probability.nextInt(1700, 2100), probability.nextInt(100, 300));
                tokenVO.addClickListener(this::tableableClicked);
                tokenVOs.add(tokenVO);
            }
        }
        shuffle(tokenVOs);

        for (int i = 0; i < gameTokensAmount.getVersatile(); i++) {
            TokenVO versatileVO = new TokenVO(new Token(null), probability.nextInt(2200, 2300), probability.nextInt(100, 300));
            versatileVO.addClickListener(this::tableableClicked);
            tokenVOs.add(versatileVO);
        }
        return tokenVOs;
    }

    private Pair<List<Card>, List<CardVO>> createCardAndVOs() {
        List<Card> cards = new ArrayList<>();
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
        return new Pair<>(cards, cardVOs);
    }

    private void tableableClicked(ViewObject viewObject) {
        Tableable tableable = (Tableable) viewObject;
        if (table.has(tableable)) {
            table.take(tableable);
        } else {
            table.put(tableable);
        }
    }

    private void cancelButtonClicked() {
        log("Canceling turn: ");
    }

    private void turnButtonClicked() {
        if (table.canGather()) {
            Turn turn = table.gather();
            game.performTurn(turn);
        }
    }

    private void log(String text) {
        textNotificationRenderer.display(text);
    }

    @Override
    public void update(double secondsElapsed) {
        updatables.forEach(updatable -> updatable.update(secondsElapsed));
    }

    public void render(org.newdawn.slick.Graphics graphics) {
        renderers.forEach(renderer -> renderer.renderOn(graphics));
    }

    void mouseClicked(int x, int y) {
        getRendererOnPoint(new Point(x, y)).ifPresent(this::clickedRenderer);
    }

    private Optional<Renderer> getRendererOnPoint(Point point) {
        return renderers
                .stream()
                .filter(Renderer::isHoverable)
                .filter(renderer -> renderer.getViewObject().getOutline().contains(point))
                .reduce((a, b) -> b);
    }

    private void clickedRenderer(Renderer renderer) {
        renderer.getViewObject().triggerClicked();
    }
}
