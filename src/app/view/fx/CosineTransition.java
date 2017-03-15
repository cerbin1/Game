package app.view.fx;

public class CosineTransition implements Transition {
    @Override
    public double valueOf(double value, double duration) {
        return Transition.cosineTransition(value);
    }
}
