package game.view.render;

import game.cards.Card;
import game.view.ImageRepository;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import static java.awt.Color.black;
import static java.awt.Color.white;

public class CardRenderer extends Renderer {
    private final BufferedImage cardImage;
    private final Card card;
    private final int cardWidth, cardHeight;

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
        graphics.setFont(new Font("Arial", Font.PLAIN, 60));
        graphics.drawString(card.getPoints() + "", cardWidth - 80, 80);

        graphics.setStroke(new BasicStroke(10));
        graphics.setColor(white);
        graphics.draw(new RoundRectangle2D.Float(
                5, 5,
                cardWidth + 10, cardHeight + 10,
                40, 40
        ));
    }
}
