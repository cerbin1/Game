package app.presenter;

import app.model.Game;
import app.model.Player;
import app.model.Updatable;
import app.model.card.Card;
import app.model.card.CardFactory;
import app.model.card.nobility.Nobility;
import app.model.token.Token;
import app.model.token.TokenColor;
import app.model.token.TokensAmount;
import app.model.util.Probability;
import app.view.SubsequentCardDealer;
import app.view.render.Tableable;
import app.view.render.renderer.*;
import app.view.render.vo.ButtonVO;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;
import app.view.render.vo.ViewObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.view.render.vo.ViewObject.slightRotation;
import static java.awt.RenderingHints.Entry;
import static java.util.Collections.shuffle;

public class GameWindow implements Updatable {
    private final static Probability probability = new Probability();

    private final List<Updatable> updatables = new ArrayList<>();
    private final List<Renderer> renderers = new ArrayList<>();

    private final PositionTable table = new PositionTable(1800, 600);

    private Game game;

    public GameWindow() {
        initializeGame();
    }

    private void initializeGame() {
        CardFactory cardFactory = new CardFactory();

        TokensAmount tokensAmount = new TokensAmount(7, 5);
        List<Player> players = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        List<Nobility> nobilities = new ArrayList<>();

        players.add(new Player());

        ButtonVO passButtonVo = new ButtonVO("Pass turn", 1600, 1600);
        passButtonVo.addClickListener(viewObject -> passButtonClicked());

        ButtonVO okButtonVo = new ButtonVO("Ok", 2100, 1600);
        passButtonVo.addClickListener(viewObject -> okButtonClicked());

        List<TokenVO> tokenVOs = new ArrayList<>();
        for (Entry<TokenColor, Integer> entry : tokensAmount.asMap().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                TokenVO tokenVO = new TokenVO(new Token(entry.getKey()), probability.nextInt(1700, 2100), probability.nextInt(100, 300));
                tokenVO.addClickListener(this::tableableClicked);
                tokenVOs.add(tokenVO);
            }
        }
        shuffle(tokenVOs);

        for (int i = 0; i < tokensAmount.getVersatile(); i++) {
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

        game = new Game(tokensAmount, players, cards, nobilities);

        new SubsequentCardDealer(cardVOs, 4, i -> 1430 - i * 238).deal();

        TextNotificationRenderer textNotificationRenderer = new TextNotificationRenderer(2000, 1000);

        updatables.addAll(cardVOs);
        updatables.addAll(tokenVOs);
        updatables.add(textNotificationRenderer);

        renderers.add(new BackgroundRenderer());

        cardVOs.forEach(vo -> renderers.add(new CardRenderer(vo)));
        tokenVOs.forEach(vo -> renderers.add(new TokenRenderer(vo)));

        renderers.add(new ButtonRenderer(passButtonVo));
        renderers.add(new ButtonRenderer(okButtonVo));
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

    private void passButtonClicked() {

    }

    private void okButtonClicked() {
        if (table.canGather()) {
            table.gather();
        }
    }

    @Override
    public void update(double secondsElapsed) {
        updatables.forEach(updatable -> updatable.update(secondsElapsed));
    }

    public void render(org.newdawn.slick.Graphics graphics) {
        renderers.forEach(renderer -> renderer.renderOn(graphics));
    }

    private Optional<Renderer> getRendererOnPoint(Point point) {
        return renderers
                .stream()
                .filter(Renderer::isHoverable)
                .filter(renderer -> renderer.getViewObject().getOutline().contains(point))
                .reduce((a, b) -> b);
    }

    public void mouseClicked(int x, int y) {
        getRendererOnPoint(new Point(x, y)).ifPresent(this::clickedRenderer);
    }

    private void clickedRenderer(Renderer renderer) {
        renderer.getViewObject().triggerClicked();
    }
}
