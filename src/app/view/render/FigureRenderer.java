package app.view.render;

import app.game.card.Figure;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;

import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Font.ITALIC;

abstract class FigureRenderer extends Renderer {
    private final Font pointsFont = new Font("Franklin Gothic Medium", ITALIC, 70);
    private final Figure figure;
    private final BufferedImage bufferedImage;

    FigureRenderer(FigureVO figureVO, BufferedImage bufferedImage) {
        super(figureVO);
        this.figure = figureVO.getFigure();
        this.bufferedImage = bufferedImage;
    }

    void drawTopHeader(Graphics2D graphics) {
        graphics.setColor(new Color(255, 255, 255, 180));
        graphics.fillRoundRect(0, 0, bufferedImage.getWidth(), 80, 20, 20);

        graphics.setColor(black);
        graphics.setFont(pointsFont);
        if (figure.getPoints() > 0) {
            drawOutlineText(graphics, figure.getPoints() + "", 20, 66);
        }
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
}
