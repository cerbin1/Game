package app.view.render;

import app.game.card.nobility.Nobility;
import app.game.token.TokenColor;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

import static app.view.ImageRepository.imageRepository;
import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Font.PLAIN;

public class NobilityRenderer extends FigureRenderer {
    private final Nobility nobility;
    private BufferedImage nobilityImage = imageRepository().nobility;
    private Font costFont = new Font("Franklin Gothic Medium", PLAIN, 30);

    public NobilityRenderer(NobilityVO nobilityVO) {
        super(nobilityVO, imageRepository().nobility);
        nobility = nobilityVO.getNobility();
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(nobilityImage, 0, 0, null);
        drawTopHeader(graphics);
        drawNobilityConditions(graphics);
        drawNobilityOutline(graphics);
    }

    private void drawNobilityConditions(Graphics2D graphics) {
        graphics.setColor(white);
        nobility.getCost().asMap().forEach(new NobilityConditionDrawer(graphics));
    }

    private void drawNobilityOutline(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(2));
        graphics.setColor(black);
        graphics.draw(new RoundRectangle2D.Float(
                0, 0,
                nobilityImage.getWidth(), nobilityImage.getHeight(),
                20, 20
        ));
    }

    private class NobilityConditionDrawer implements BiConsumer<TokenColor, Integer> {
        private final Graphics2D graphics;
        private int elementsRendered = 0;

        NobilityConditionDrawer(Graphics2D graphics) {
            this.graphics = graphics;
        }

        @Override
        public void accept(TokenColor color, Integer amount) {
            if (amount == 0) return;
            graphics.setFont(costFont);
            graphics.drawString(color.name() + ": " + amount, 19, nextElementHeight());
        }

        private int nextElementHeight() {
            return nobilityImage.getHeight() - 15 - elementsRendered++ * 45;
        }
    }
}
