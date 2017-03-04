package app.view.render;

import app.game.Updatable;
import app.view.fx.LinearTransition;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class ViewObject implements Updatable {
    private final AnimatedValue x, y, rotation;
    private final int width, height;
    private double secondsPassed = 0;

    ViewObject(int x, int y, int width, int height) {
        this.x = new AnimatedValue(x, new LinearTransition());
        this.y = new AnimatedValue(y, new LinearTransition());
        this.rotation = new AnimatedValue(0);
        this.width = width;
        this.height = height;
    }

    int getX() {
        return (int) round(x.getValue());
    }

    int getY() {
        return (int) round(y.getValue());
    }

    public void setRotation(double rotation) {
        this.rotation.setValue(rotation);
    }

    double getRotation() {
        return rotation.getValue();
    }

    public void update(double secondsPassed) {
        this.secondsPassed += secondsPassed;
        x.update(secondsPassed);
        y.update(secondsPassed);
        rotation.update(secondsPassed);
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    double getPerspectiveX() {
        return 1.0;
    }

    double getPerspectiveY() {
        return 1.0;
    }

    boolean isFlipped() {
        return getPerspectiveY() < 0 || getPerspectiveX() < 0;
    }

    public void moveTo(int x, int y, double duration) {
        this.x.setValue(x, duration);
        this.y.setValue(y, duration);
    }

    public void reposition(int x, int y, double duration) {
        moveTo(x, y, duration);
        setRotation(slightRotation());
    }

    public static double slightRotation() {
        return random() * 0.14 - 0.07;
    }
}
