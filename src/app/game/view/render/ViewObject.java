package app.game.view.render;

import app.game.Updatable;
import app.game.view.fx.Transition;

import static java.lang.Math.round;

public class ViewObject implements Updatable {
    private final AnimatedValue x, y;
    private final int width, height;
    private double secondsPassed = 0;

    ViewObject(int x, int y, int width, int height) {
        this.x = new AnimatedValue(x);
        this.y = new AnimatedValue(y);
        this.width = width;
        this.height = height;
    }

    int getX() {
        return (int) round(x.getValue());
    }

    int getY() {
        return (int) round(y.getValue());
    }

    double getRotation() {
        return 0.0;
    }

    public void update(double secondsPassed) {
        this.secondsPassed += secondsPassed;
        x.update(secondsPassed);
        y.update(secondsPassed);
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    double getPerspectiveX() {
        return Transition.cosineTransition(secondsPassed);
    }

    double getPerspectiveY() {
        return 1.0;
    }

    boolean isFlipped() {
        return getPerspectiveX() < 0;
    }

    public void moveTo(int x, int y) {
        this.x.setValue(x);
        this.y.setValue(y);
    }
}
