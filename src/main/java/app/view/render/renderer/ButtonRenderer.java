package app.view.render.renderer;

import app.view.render.vo.ButtonVO;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;

import static app.view.ImageRepository.imageRepository;
import static java.awt.Color.white;
import static java.awt.Font.ITALIC;

public class ButtonRenderer extends Renderer {
    private final Font pointsFont = new Font("Franklin Gothic Medium", ITALIC, 70);
    private final BufferedImage buttonImage;

    public ButtonRenderer(ButtonVO buttonVO) {
        super(buttonVO);
        buttonImage = imageRepository().button;
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.scale(1.6, 1.6);
        graphics.drawImage(buttonImage, 0, 0, null);
        graphics.scale(1 / 1.6, 1 / 1.6);
        drawText(graphics, "PASS TURN", 160, 80);
    }

    private void drawText(Graphics2D graphics, String text, int x, int y) {
        graphics.translate(x, y);
        GlyphVector glyphVector = pointsFont.createGlyphVector(graphics.getFontRenderContext(), text);
        Shape textShape = glyphVector.getOutline();

        graphics.setColor(white);
        graphics.fill(textShape);
        graphics.translate(-x, -y);
    }
}
