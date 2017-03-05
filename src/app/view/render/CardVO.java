package app.view.render;

import app.game.card.Card;

import static java.lang.Math.PI;

public class CardVO extends ViewObject {
    private final static double RESERVATION_ANIMATION_LENGTH = 2.5;

    private final Card card;
    private AnimatedValue reservation = new AnimatedValue(0.0);
    private AnimatedValue flip = new AnimatedValue(-1.0);

    public CardVO(Card card, int x, int y) {
        super(x, y, 236, 330);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public void setFlipped(boolean flipped) {
        flip.setValue(flipped ? 1.0 : -1.0, 2.0);
    }

    @Override
    public void update(double secondsPassed) {
        super.update(secondsPassed);
        reservation.setValue(card.isReserved() ? 1.0 : 0.0, RESERVATION_ANIMATION_LENGTH);
        reservation.update(secondsPassed);
        flip.update(secondsPassed);
    }

    @Override
    double getRotation() {
        return super.getRotation() + reservation.getValue() * PI / 2;
    }

    @Override
    double getPerspectiveX() {
        return flip.getValue();
    }
}