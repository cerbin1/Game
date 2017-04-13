package app.view.render.renderer;

import app.model.card.Figure;
import app.model.token.TokenColor;
import app.view.render.vo.FigureVO;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import java.util.function.BiConsumer;

import static org.newdawn.slick.Color.black;
import static org.newdawn.slick.Color.white;

abstract class FigureRenderer extends Renderer {
    //    private final Font pointsFont = new Font("Franklin Gothic Medium", ITALIC, 70);
//    private final Font costFont = new Font("Franklin Gothic Medium", PLAIN, 40);
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

//        graphics.setColor(black);
        // graphics.setFont(pointsFont);
        if (figure.getPoints() > 0) {
            drawOutlineText(graphics, figure.getPoints() + "", 20, 66);
        }
    }

    private void drawOutlineText(Graphics graphics, String text, int x, int y) {
//        graphics.translate(x, y);
//        GlyphVector glyphVector = pointsFont.createGlyphVector(graphics.getFontRenderContext(), text);
//        Shape textShape = glyphVector.getOutline();
//
//        graphics.setColor(black);
//        graphics.setStroke(new BasicStroke(2.0f));
//        graphics.draw(textShape);
//
//        graphics.setColor(white);
//        graphics.fill(textShape);
//        graphics.translate(-x, -y);
    }

    private void drawCost(Graphics graphics) {
        graphics.setColor(white);
        figure.getCost().asMap().forEach(new CostDrawer(graphics));
    }

    private void drawOutline(Graphics graphics) {
//        graphics.setStroke(new BasicStroke(2));
//        graphics.setColor(black);
//        graphics.draw(new Rectangle(
//                0f, 0f,
//                figureImage.getWidth(), figureImage.getHeight()
//        ));
    }

    private class CostDrawer implements BiConsumer<TokenColor, Integer> {
        private final Graphics graphics;
        private int elementsRendered = 0;

        CostDrawer(Graphics graphics) {
            this.graphics = graphics;
        }

        @Override
        public void accept(TokenColor color, Integer amount) {
            if (amount == 0) return;
//            graphics.setFont(costFont);
            graphics.drawString(color.name() + ": " + amount, 19, nextElementHeight());
        }

        private int nextElementHeight() {
            return figureImage.getHeight() - 15 - elementsRendered++ * 45;
        }
    }
}
