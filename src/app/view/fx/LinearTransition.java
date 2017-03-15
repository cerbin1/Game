package app.view.fx;

public class LinearTransition implements Transition {
    @Override
    public double valueOf(double value, double duration) {
        return Transition.linearTransition(value);
    }
}
