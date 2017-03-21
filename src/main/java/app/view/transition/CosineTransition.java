package app.view.transition;

public class CosineTransition implements Transition {
    @Override
    public double valueOf(double value) {
        return Transition.cosineTransition(value);
    }
}
