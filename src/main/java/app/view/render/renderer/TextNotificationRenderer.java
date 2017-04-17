package app.view.render.renderer;

import app.model.Updatable;
import app.view.render.vo.ViewObject;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class TextNotificationRenderer extends Renderer implements Updatable {
    private Map<String, Double> texts = new LinkedHashMap<>();
    private double timer = 0;

    public TextNotificationRenderer(int x, int y) {
        super(new ViewObject(x, y, 300, 50) {
        });
    }

    @Override
    protected void render(Graphics2D graphics) {
        texts.forEach((text, time) -> {
            if (timer < time) {
                graphics.drawString(text, 20, 20);
            }
        });
    }

    @Override
    public void update(double secondsPassed) {
        timer += secondsPassed;
    }

    public void display(String text) {
        display(text, 3.0);
    }

    public void display(String text, double time) {
        texts.put(text, timer + time);
    }
}
