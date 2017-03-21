package app.view.transition;

public class InOutQuartTransition implements Transition {
    @Override
    public double valueOf(double value) {
        return Transition.inOutQuart(value);
    }
}
