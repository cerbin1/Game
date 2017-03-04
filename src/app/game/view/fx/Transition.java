package app.game.view.fx;

import static java.lang.Math.*;

public interface Transition {
    double valueOf(double value);

    static double linearTransition(double value) {
        return value;
    }

    static double cosineTransition(double value) {
        return -(cos(value * PI) - 1) / 2;
    }

    static double outCubic(double value) {
        return pow(value, 3);
    }
}
