package app.game.view.fx;

public class InOutQuartTransition implements Transition {
    @Override
    public double valueOf(double value) {
        return Transition.inOutQuart(value);
    }
}
