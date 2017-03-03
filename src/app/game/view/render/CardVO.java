package app.game.view.render;

import app.game.cards.Card;

import static java.lang.Math.*;

public class CardVO extends ViewObject {
    private final static double RESERVATION_ANIM_LENGTH = 2.5;

    private final Card card;
    private double reservationProgress = 0;

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
        updateReservationProgress(secondsPassed);
    }

    private void updateReservationProgress(double secondsPassed) {
        int direction = card.isReserved() ? 1 : -1;
        reservationProgress += direction * secondsPassed;
        reservationProgress = max(min(reservationProgress, RESERVATION_ANIM_LENGTH), 0);
    }

    @Override
    double getRotation() {
        return reservationProgress / RESERVATION_ANIM_LENGTH * PI / 2;
    }
}
