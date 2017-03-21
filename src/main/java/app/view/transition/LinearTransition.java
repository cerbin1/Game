package app.view.transition;

public class LinearTransition implements Transition {
    @Override
    public double valueOf(double value) {
        return Transition.linearTransition(value);
    }
}
