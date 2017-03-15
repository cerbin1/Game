package app.view.fx;

public class InOutQuartTransition implements Transition {
    @Override
    public double valueOf(double value, double duration) {
        return Transition.inOutQuart(value);
    }
}
