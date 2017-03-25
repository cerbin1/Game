package app.presenter;

import app.model.GameBuilder;
import app.model.Player;
import app.model.Updatable;
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
import static java.util.Collections.*;

public class GameWindow implements Updatable {
    private final static Probability probability = new Probability();

    private final BufferWindow window = new BufferWindow();

    private final List<Updatable> updatables = new ArrayList<>();
    private final List<Renderer> renderers = new ArrayList<>();

    private Point currentVoPreviousPoint;
    private ViewObject currentVO;

    private final PositionTable table = new PositionTable(1800, 600);

    public GameWindow() {
        window.addMouseListener(new GameMouseAdapter());
        initializeGame();
    }

    private void initializeGame() {
        CardFactory cardFactory = new CardFactory();
        GameBuilder builder = new GameBuilder();
        Tokens tokens = new Tokens(7, 5);
        Player player = new Player();

        List<CardVO> cardVOs = new ArrayList<>();
        List<TokenVO> tokenVOs = new ArrayList<>();
        NobilityVO nobilityVO = new NobilityVO(new Nobility(new Tokens(1, 2, 3, 4, 0), 3), 1000, 1500);
        ButtonVO buttonVO = new ButtonVO(1600, 1600);

        for (Entry<TokenColor, Integer> entry : tokens.asMap().entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                TokenVO tokenVO = new TokenVO(new Token(entry.getKey()), probability.nextInt(1700, 2100), probability.nextInt(100, 300));
                tokenVO.addClickListener(this::tableableClicked);
                tokenVOs.add(tokenVO);
            }
        }
        shuffle(tokenVOs);

        FpsRenderer fpsRenderer = new FpsRenderer(3130, 80);

        for (int i = 0; i < tokens.getVersatile(); i++) {
            TokenVO versatileVO = new TokenVO(new Token(null), probability.nextInt(2200, 2300), probability.nextInt(100, 300));
            versatileVO.addClickListener(this::tableableClicked);
            tokenVOs.add(versatileVO);
        }

        for (int i = 0; i < 4; i++) {
            CardVO vo = new CardVO(cardFactory.createCheapCard(), 300, 200);
            vo.addClickListener(this::tableableClicked);
            vo.setRotation(slightRotation());
            cardVOs.add(vo);
        }

        for (int i = 0; i < 4; i++) {
            CardVO vo = new CardVO(cardFactory.createMediumCard(), 300, 530);
            vo.addClickListener(this::tableableClicked);
            vo.setRotation(slightRotation());
            cardVOs.add(vo);
        }
        for (int i = 0; i < 4; i++) {
            CardVO vo = new CardVO(cardFactory.createExpensiveCard(), 300, 860);
            vo.addClickListener(this::tableableClicked);
            vo.setRotation(slightRotation());
            cardVOs.add(vo);
        }

        new SubsequentCardDealer(cardVOs, 4, i -> 1430 - i * 238).deal();

        updatables.addAll(cardVOs);
        updatables.addAll(tokenVOs);
        updatables.add(nobilityVO);
        updatables.add(fpsRenderer);

        renderers.add(new BackgroundRenderer());
        renderers.add(new NobilityRenderer(nobilityVO));
        renderers.add(fpsRenderer);
        cardVOs.forEach(vo -> renderers.add(new CardRenderer(vo)));
        tokenVOs.forEach(vo -> renderers.add(new TokenRenderer(vo)));

        renderers.add(new ButtonRenderer(buttonVO));
    }

    private void tableableClicked(ViewObject viewObject) {
        int x = viewObject.getDestinationX(), y = viewObject.getDestinationY();
        Tableable tableable = (Tableable) viewObject;
        if (table.has(tableable)) {
            Point point = table.getPoint(tableable);
            table.take(tableable);
            viewObject.moveTo(point.x, point.y, 2.0);
        } else if (table.put(tableable, new Point(x, y))) {
            viewObject.moveTo(1800, 600, 2.0);
        }
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
