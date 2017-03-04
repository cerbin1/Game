package app.game.view.render;

import static java.lang.Double.compare;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class AnimatedValue {
    private double passedDuration = 0.0;
    private double duration;
    private double startValue;
    private double currentValue;
    private double destinationValue;

    public AnimatedValue(double value) {
        this.currentValue = value;
        this.destinationValue = value;
    }

    public void setValue(double value) {
        setValue(value, 1.0);
    }

    public void setValue(double value, double duration) {
        this.startValue = currentValue;
        this.destinationValue = value;
        this.duration = duration;
        this.passedDuration = 0.0;
    }

    public double getValue() {
        return currentValue;
    }

    public void update(double seconds) {
        int compare = compare(startValue, destinationValue);
        this.passedDuration += seconds;

        double progress = passedDuration / duration;

        double difference = destinationValue - startValue;
        if (compare < 0) {
            currentValue = min(startValue + difference * progress, destinationValue);
        }
        if (compare > 0) {
            currentValue = max(startValue + difference * progress, destinationValue);
        }
    }
}
