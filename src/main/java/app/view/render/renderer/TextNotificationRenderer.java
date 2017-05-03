package app.view.render.renderer;

import app.model.Updatable;
import app.view.render.vo.ViewObject;
import org.newdawn.slick.Graphics;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import static app.view.util.Font.POINTS_FONT;

public class TextNotificationRenderer extends Renderer implements Updatable {
    private Map<String, Double> texts = new LinkedHashMap<>();
    private double timer = 0;

    public TextNotificationRenderer(int x, int y) {
        super(new ViewObject(x, y, 300, 50) {
        });
    }

    @Override
    protected void render(Graphics graphics) {
        texts.keySet().forEach(new MultipleTextNotificationDisplayer(graphics));
    }

    @Override
    public void update(double secondsPassed) {
        timer += secondsPassed;
        texts.values().removeIf(time -> time <= timer + 0);
    }

    public void display(String text) {
        display(text, 3.0);
    }

    void display(String text, double time) {
        if (texts.containsKey(text)) {
            display(text + "*", time);
            return;
        }
        texts.put(text, timer + time);
    }

    private static class MultipleTextNotificationDisplayer implements Consumer<String> {
        private final Graphics graphics;
        private int index = 0;

        MultipleTextNotificationDisplayer(Graphics graphics) {
            this.graphics = graphics;
        }

        @Override
        public void accept(String text) {
            graphics.setFont(POINTS_FONT);
            graphics.drawString(text, 20, 20 + index * 30);
            index++;
        }
    }
}
