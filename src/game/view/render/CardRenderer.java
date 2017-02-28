package game.view.render;

import game.view.ImageRepository;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import static java.awt.Color.white;

public class CardRenderer extends Renderer {
    private final BufferedImage cardImage;
    private final int cardWidth, cardHeight;

    public CardRenderer(CardVO cardVO, ImageRepository imageRepository) {
        super(cardVO);
        this.cardImage = imageRepository.card;

        cardWidth = cardImage.getWidth();
        cardHeight = cardImage.getHeight();
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(cardImage, 10, 10, null);

        graphics.setStroke(new BasicStroke(10));
        graphics.setColor(white);
        graphics.draw(new RoundRectangle2D.Float(
                5, 5,
                cardWidth + 10, cardHeight + 10,
                40, 40
        ));
    }
}
