package app.view.render.renderer;

import app.model.Updatable;
import app.view.render.vo.ViewObject;

import java.awt.*;
import java.awt.font.GlyphVector;
import java.text.DecimalFormat;

import static app.model.util.Fonts.FPS_FONT;
import static java.awt.Color.*;

public class FpsRenderer extends Renderer implements Updatable {
    private int frames = 0;
    private double seconds = 0.0;

    public FpsRenderer(int x, int y) {
        super(new ViewObject(x, y, 100, 50) {
        });
    }

    @Override
    protected void render(Graphics2D graphics) {
        frames++;

        double fps = frames / seconds;
        graphics.setColor(WHITE);
        drawOutlineText(graphics, new DecimalFormat("00.0").format(fps) + "", -50, 50);
    }

    @Override
    public void update(double secondsPassed) {
        seconds += secondsPassed;
        if (seconds >= 5.0) {
            seconds = 0;
            frames = 0;
        }
    }

    private void drawOutlineText(Graphics2D graphics, String text, int x, int y) {
        graphics.translate(x, y);
        GlyphVector glyphVector = FPS_FONT.createGlyphVector(graphics.getFontRenderContext(), text);
        Shape textShape = glyphVector.getOutline();

        graphics.setColor(black);
        graphics.setStroke(new BasicStroke(6));
        graphics.draw(textShape);

        graphics.setColor(white);
        graphics.fill(textShape);
        graphics.translate(-x, -y);
    }

    @Override
    public boolean isHoverable() {
        return false;
    }
}
