package app.game.view.fx;

public class LinearTransition implements Transition {
    @Override
    public double valueOf(double value) {
        return Transition.linearTransition(value);
    }
}
