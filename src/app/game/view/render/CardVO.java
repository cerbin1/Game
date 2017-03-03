package app.game.view.render;

import app.game.card.Card;

import static java.lang.Math.*;

public class CardVO extends ViewObject {
    private final static double RESERVATION_ANIM_LENGTH = 2.5;

    private final Card card;
    private AnimatedValue reservation = new AnimatedValue(0.0, RESERVATION_ANIM_LENGTH);

    public CardVO(Card card, int x, int y) {
        super(x, y, 236, 330);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public void update(double secondsPassed) {
        super.update(secondsPassed);
        reservation.setValue(card.isReserved() ? 1.0 : 0.0);
        reservation.update(secondsPassed);
    }

    @Override
    double getRotation() {
        return reservation.getValue() * PI / 2;
    }
}
