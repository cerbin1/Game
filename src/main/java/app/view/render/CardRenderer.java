package app.view.render;

import app.view.render.vo.CardVO;

import java.awt.*;
import java.awt.image.BufferedImage;

import static app.view.ImageRepository.imageRepository;

public class CardRenderer extends FigureRenderer {
    private final BufferedImage cardBack;

    public CardRenderer(CardVO cardVO) {
        super(cardVO, imageRepository().cardCastle);
        cardBack = imageRepository().cardBack;
    }

    @Override
    protected void render(Graphics2D graphics) {
        if (viewObject.isFlipped()) {
            graphics.drawImage(cardBack, 0, 0, null);
        } else {
            super.render(graphics);
        }
    }
}
