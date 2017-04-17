package app.view.render.renderer;

import app.model.Updatable;
import app.view.render.vo.ViewObject;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TextNotificationRenderer extends Renderer implements Updatable {
    private Map<String, Double> texts = new LinkedHashMap<>();
    private double timer = 0;

    public TextNotificationRenderer(int x, int y) {
        super(new ViewObject(x, y, 300, 50) {
        });
    }

    @Override
    protected void render(Graphics2D graphics) {
        texts


                .entrySet()
                .stream()
                .filter(stringDoubleEntry -> stringDoubleEntry.getValue() >= timer)
                .forEach(new MultipleTextNotificationDisplayer(graphics));
    }

    @Override
    public void update(double secondsPassed) {
        timer += secondsPassed;
    }

    void display(String text) {
        display(text, 3.0);
    }

    void display(String text, double time) {
        texts.put(text, timer + time);
    }

    private static class MultipleTextNotificationDisplayer implements Consumer<Map.Entry<String, Double>> {
        private final Graphics2D graphics;
        private int index;

        MultipleTextNotificationDisplayer(Graphics2D graphics) {
            this.graphics = graphics;
            index = 0;
        }

        @Override
        public void accept(Map.Entry<String, Double> text) {
            graphics.drawString(text.getKey(), 20, 20 + index * 30);
            index++;
        }
    }
}
