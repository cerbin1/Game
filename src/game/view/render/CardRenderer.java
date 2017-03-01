package game.view.render;

import game.cards.Card;
import game.view.ImageRepository;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import static java.awt.Color.black;
import static java.awt.Color.white;

public class CardRenderer extends Renderer {
    private final BufferedImage cardImage;
    private final Card card;
    private final int cardWidth, cardHeight;
    private Font arial = new Font("Arial", Font.ITALIC, 70);

    public CardRenderer(CardVO cardVO, ImageRepository imageRepository) {
        super(cardVO);
        this.card = cardVO.getCard();
        this.cardImage = imageRepository.card;

        cardWidth = cardImage.getWidth();
        cardHeight = cardImage.getHeight();
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(cardImage, 10, 10, null);

        graphics.setColor(new Color(255, 255, 255, 180));
        graphics.fillRect(10, 10, cardWidth, 80);

        graphics.setColor(black);
        graphics.setFont(arial);
        drawOutlineText(graphics, card.getPoints() + "", 30, 76);

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
        GlyphVector glyphVector = arial.createGlyphVector(graphics.getFontRenderContext(), text);
        Shape textShape = glyphVector.getOutline();

        graphics.setColor(black);
        graphics.setStroke(new BasicStroke(2.0f));
        graphics.draw(textShape);

        graphics.setColor(white);
        graphics.fill(textShape);
        graphics.translate(-x, -y);
    }
}
