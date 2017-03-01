package game.view.render;

import game.TokenColor;
import game.Tokens;
import game.cards.Card;
import game.view.ImageRepository;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.function.BiConsumer;

import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;

public class CardRenderer extends Renderer {
    private final BufferedImage cardImage;
    private final Card card;
    private final Tokens cardCost;
    private final int cardWidth, cardHeight;
    private Font pointsFont = new Font("Franklin Gothic Medium", ITALIC, 70);
    private Font costFont = new Font("Franklin Gothic Medium", PLAIN, 40);

    public CardRenderer(CardVO cardVO, ImageRepository imageRepository) {
        super(cardVO);
        card = cardVO.getCard();
        cardCost = card.getCost();
        cardImage = imageRepository.card;

        cardWidth = cardImage.getWidth();
        cardHeight = cardImage.getHeight();
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(cardImage, 10, 10, null);
        drawTopHeader(graphics);
        drawCardCosts(graphics);
        drawCardOutline(graphics);
    }

    private void drawTopHeader(Graphics2D graphics) {
        graphics.setColor(new Color(255, 255, 255, 180));
        graphics.fillRect(10, 10, cardWidth, 80);

        graphics.setColor(black);
        graphics.setFont(pointsFont);
        drawOutlineText(graphics, card.getPoints() + "", 30, 76);
    }

    private void drawCardCosts(Graphics2D graphics) {
        cardCost.asMap().forEach(new CardCostDrawer(graphics));
    }

    private void drawCardOutline(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(10));
        graphics.setColor(white);
        graphics.draw(new RoundRectangle2D.Float(
                5, 5,
                cardWidth + 10, cardHeight + 10,
                40, 40
        ));
    }

    private void drawOutlineText(Graphics2D graphics, String text, int x, int y) {
        graphics.translate(x, y);
        GlyphVector glyphVector = pointsFont.createGlyphVector(graphics.getFontRenderContext(), text);
        Shape textShape = glyphVector.getOutline();

        graphics.setColor(black);
        graphics.setStroke(new BasicStroke(2.0f));
        graphics.draw(textShape);

        graphics.setColor(white);
        graphics.fill(textShape);
        graphics.translate(-x, -y);
    }

    class CardCostDrawer implements BiConsumer<TokenColor, Integer> {
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
            elementsRendered++;
        }

        private int nextElementHeight() {
            return cardHeight - 15 - elementsRendered * 45;
        }
    }
}
