package app.view.render.renderer.vo;

import app.view.render.vo.CardVO;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import static app.view.ImageRepository.imageRepository;

public class CardRenderer extends FigureRenderer {
    private final Image cardBack;

    public CardRenderer(CardVO cardVO) {
        super(cardVO, imageRepository().cardCastle);
        cardBack = imageRepository().cardBack;
    }

    @Override
    protected void render(Graphics graphics) {
        if (viewObject.isFlipped()) {
            cardBack.draw(0, 0);
        } else {
            super.render(graphics);
        }
    }
}
