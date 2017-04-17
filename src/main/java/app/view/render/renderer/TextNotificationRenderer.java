package app.view.render.renderer;

import app.model.Updatable;
import app.view.render.vo.ViewObject;

import java.awt.*;

public class TextNotificationRenderer extends Renderer implements Updatable {
    private final double seconds;
    private double timer = 0;

    public TextNotificationRenderer(int x, int y) {
        super(new ViewObject(x, y, 300, 50) {
        });
        this.seconds = 3;
    }

    @Override
    protected void render(Graphics2D graphics) {
        if (timer < seconds) {
            graphics.drawString("", 30, 30);
        }
    }

    @Override
    public void update(double secondsPassed) {
        timer += secondsPassed;
    }

    public void display(String string) {

    }
}
