package app.view.render.renderer;

import app.model.Updatable;
import app.view.render.vo.ViewObject;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;

import static java.awt.Color.WHITE;
import static java.awt.Color.black;

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
        graphics.drawString(new DecimalFormat("##.#").format(fps) + "", 0, 0);
        drawOutline(graphics);
    }

    @Override
    public void update(double secondsPassed) {
        seconds += secondsPassed;
        if (seconds >= 5.0) {
            seconds = 0;
            frames = 0;
        }
    }

    private void drawOutline(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(black);
        graphics.draw(new RoundRectangle2D.Float(
                -10, -35,
                100, 50,
                20, 20
        ));
    }

    @Override
    public boolean isHoverable() {
        return false;
    }
}
