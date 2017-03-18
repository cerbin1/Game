package app.view.render;

import app.game.card.Card;
import app.game.token.TokenColor;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

import static app.view.ImageRepository.imageRepository;
import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Font.PLAIN;

public class CardRenderer extends FigureRenderer {
    private final Card card;
    private final BufferedImage cardImage, cardBack;
    private final Font costFont = new Font("Franklin Gothic Medium", PLAIN, 40);

    public CardRenderer(CardVO cardVO) {
        super(cardVO, imageRepository().cardCastle);
        card = cardVO.getCard();
        cardImage = imageRepository().cardCastle;
        cardBack = imageRepository().cardBack;
    }

    @Override
    protected void render(Graphics2D graphics) {
        if (viewObject.isFlipped()) {
            graphics.drawImage(cardBack, 0, 0, null);
        } else {
            graphics.drawImage(cardImage, 0, 0, null);
            drawTopHeader(graphics);
            drawCardCosts(graphics);
            drawCardOutline(graphics);
        }
    }

    private void drawCardCosts(Graphics2D graphics) {
        graphics.setColor(white);
        card.getCost().asMap().forEach(new CardCostDrawer(graphics));
    }

    private void drawCardOutline(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(2));
        graphics.setColor(black);
        graphics.draw(new RoundRectangle2D.Float(
                0, 0,
                cardImage.getWidth(), cardImage.getHeight(),
                20, 20
        ));
    }

    private class CardCostDrawer implements BiConsumer<TokenColor, Integer> {
        private final Graphics2D graphics;
        private int elementsRendered = 0;

        CardCostDrawer(Graphics2D graphics) {
            this.graphics = graphics;
        }

        @Override
        public void accept(TokenColor color, Integer amount) {
            if (amount == 0) return;
            graphics.setFont(costFont);
            graphics.drawString(color.name() + ": " + amount, 19, nextElementHeight());
        }

        private int nextElementHeight() {
            return cardImage.getHeight() - 15 - elementsRendered++ * 45;
        }
    }
}
