package app.game.view.render;

import app.game.view.fx.Transition;

public class ViewObject {
    private int x, y, width, height;
    private double secondsPassed = 0;

    ViewObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    double getRotation() {
        return 0.0;
    }

    public void update(double secondsPassed) {
        this.secondsPassed += secondsPassed;
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
}
