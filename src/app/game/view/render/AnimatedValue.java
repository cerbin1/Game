package app.game.view.render;

import static java.lang.Double.compare;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class AnimatedValue {
    private final double duration;
    private double currentValue;
    private double destinationValue;

    public AnimatedValue(double value) {
        this(value, 1.0);
    }

    public AnimatedValue(double value, double secondsDuration) {
        this.currentValue = value;
        this.destinationValue = value;
        this.duration = secondsDuration;
    }

    public void setValue(double value) {
        this.destinationValue = value;
    }

    public double getValue() {
        return currentValue;
    }

    public void update(double seconds) {
        int compare = compare(currentValue, destinationValue);
        if (compare < 0) {
            currentValue = min(currentValue + seconds / duration, destinationValue);
        }
        if (compare > 0) {
            currentValue = max(currentValue - seconds / duration, destinationValue);
        }
    }
}
