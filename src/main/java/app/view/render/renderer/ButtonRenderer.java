package app.view.render.renderer;

import app.view.render.vo.ButtonVO;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.*;

import static app.view.ImageRepository.imageRepository;
import static java.awt.Font.ITALIC;

public class ButtonRenderer extends Renderer {
    private final Font pointsFont = new Font("Franklin Gothic Medium", ITALIC, 70);
    private final Image buttonImage;

    public ButtonRenderer(ButtonVO buttonVO) {
        super(buttonVO);
        buttonImage = imageRepository().button;
    }

    @Override
    protected void render(Graphics graphics) {
        graphics.scale(1.6f, 1.6f);
        graphics.drawImage(buttonImage, 0, 0, null);
        graphics.scale(1 / 1.6f, 1 / 1.6f);
        drawText(graphics, "PASS TURN", 160, 80);
    }

    private void drawText(Graphics graphics, String text, int x, int y) {
//        graphics.translate(x, y);
//        GlyphVector glyphVector = pointsFont.createGlyphVector(
//                new FontRenderContext(new AffineTransform(), true, true), text);
//        Shape textShape = glyphVector.getOutline();
//
//        graphics.setColor(white);
//        graphics.fill(textShape);
//        graphics.translate(-x, -y);
    }
}
