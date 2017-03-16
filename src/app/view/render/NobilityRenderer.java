package app.view.render;

import app.game.card.nobility.Nobility;
import app.view.ImageRepository;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;

import static java.awt.Color.black;
import static java.awt.Color.white;
import static java.awt.Font.ITALIC;
import static java.awt.Font.PLAIN;

public class NobilityRenderer extends Renderer {
    private final BufferedImage nobilityImage;
    private final Nobility nobility;
    private Font pointsFont = new Font("Franklin Gothic Medium", ITALIC, 70);
    private Font costFont = new Font("Franklin Gothic Medium", PLAIN, 40);

    public NobilityRenderer(NobilityVO nobilityVO) {
        super(nobilityVO);
        nobility = nobilityVO.getNobility();
        nobilityImage = ImageRepository.imageRepository().nobility;
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(nobilityImage, 0, 0, null);
        drawTopHeader(graphics);
    }

    private void drawTopHeader(Graphics2D graphics) {
        graphics.setColor(new Color(255, 255, 255, 180));
        graphics.fillRoundRect(0, 0, nobilityImage.getWidth(), 80, 20, 20);

        graphics.setColor(black);
        graphics.setFont(pointsFont);
        if (nobility.getPoints() > 0) {
            drawOutlineText(graphics, nobility.getPoints() + "", 20, 66);
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
