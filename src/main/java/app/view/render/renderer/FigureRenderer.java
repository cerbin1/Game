package app.view.render.renderer;

import app.model.card.Figure;
import app.model.token.TokenColor;
import app.view.render.vo.FigureVO;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import java.util.function.BiConsumer;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import static org.newdawn.slick.Color.white;

abstract class FigureRenderer extends Renderer {
    private final java.awt.Font pointsFont = new java.awt.Font("Franklin Gothic Medium", BOLD, 52);
    private final java.awt.Font costFont = new java.awt.Font("Franklin Gothic Medium", PLAIN, 40);

    TrueTypeFont ttf1 = new TrueTypeFont(pointsFont, true);
    TrueTypeFont ttf2 = new TrueTypeFont(costFont, true);

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
            ttf1.drawString(20, 6, figure.getPoints() + "", Color.black);
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
            ttf2.drawString(19, nextElementHeight(), color.name() + ": " + amount);
        }

        private int nextElementHeight() {
            return figureImage.getHeight() - 45 - elementsRendered++ * 45;
        }
    }
}
