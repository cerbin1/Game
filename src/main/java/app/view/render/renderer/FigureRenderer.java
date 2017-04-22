package app.view.render.renderer;

import app.model.card.Figure;
import app.model.token.TokenColor;
import app.view.render.vo.FigureVO;
import app.view.util.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.function.BiConsumer;

import static org.newdawn.slick.Color.white;

abstract class FigureRenderer extends Renderer {

    private final Figure figure;
    private final Image figureImage;

    FigureRenderer(FigureVO figureVO, Image figureImage) {
        super(figureVO);
        this.figure = figureVO.getFigure();
        this.figureImage = figureImage;
    }

    @Override
    protected void render(Graphics graphics) {
        graphics.drawImage(figureImage, 0, 0, null);
        drawTopHeader(graphics);
        drawCost(graphics);
        drawOutline(graphics);
    }

    private void drawTopHeader(Graphics graphics) {
        graphics.setColor(white);
        graphics.fillRoundRect(0, 0, figureImage.getWidth(), 80, 20, 20);

        if (figure.getPoints() > 0) {
            Font.POINTS.drawString(20, 6, figure.getPoints() + "", Color.black);
        }
    }

    private void drawCost(Graphics graphics) {
        graphics.setColor(white);
        figure.getCost().asMap().forEach(new CostDrawer());
    }

    private void drawOutline(Graphics graphics) {

    }

    private class CostDrawer implements BiConsumer<TokenColor, Integer> {
        private int elementsRendered = 0;

        @Override
        public void accept(TokenColor color, Integer amount) {
            if (amount == 0) return;
            Font.COST.drawString(19, nextElementHeight(), color.name() + ": " + amount);
        }

        private int nextElementHeight() {
            return figureImage.getHeight() - 45 - elementsRendered++ * 45;
        }
    }
}
