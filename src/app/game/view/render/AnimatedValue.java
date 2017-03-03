package app.game.view.render;

import static java.lang.Double.compare;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class AnimatedValue {
    private final double changePerSecond;
    private double currentValue;
    private double destinationValue;

    public AnimatedValue(double value) {
        this(value, 1.0);
    }

    public AnimatedValue(double value, double changePerSecond) {
        this.currentValue = value;
        this.destinationValue = value;
        this.changePerSecond = changePerSecond;
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
            currentValue = min(currentValue + seconds * changePerSecond, destinationValue);
        }
        if (compare > 0) {
            currentValue = max(currentValue - seconds * changePerSecond, destinationValue);
        }
    }
}
