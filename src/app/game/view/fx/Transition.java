package app.game.view.fx;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class Transition {
    public static double cosineTransition(double value) {
        return cos(value * PI);
    }
}
