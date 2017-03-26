package app.view.render.vo;

import app.model.card.Card;
import app.view.render.AnimatedValue;
import app.view.render.Operator;
import app.view.render.Tableable;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import static java.lang.Math.PI;

public class CardVO extends FigureVO implements Tableable {
    private final static double RESERVATION_ANIMATION_LENGTH = 2.5;

    private final Card card;
    private final AnimatedValue reservation = new AnimatedValue(0.0);
    private final AnimatedValue flip = new AnimatedValue(-1.0);
    private final int x, y;

    public CardVO(Card card, int x, int y) {
        super(card, x, y, 236, 330);
        this.card = card;
        this.x = x;
        this.y = y;
    }

    public Card getCard() {
        return card;
    }

    public void setFlipped(boolean flipped) {
        flip.setValue(flipped ? 1.0 : -1.0, 0.6);
    }

    @Override
    protected Shape getBaseOutline() {
        return new RoundRectangle2D.Double(
                getX() - width / 2, getY() - height / 2, width, height,
                20, 20
        );
    }

    @Override
    public void update(double secondsPassed) {
        super.update(secondsPassed);
        reservation.setValue(card.isReserved() ? 1.0 : 0.0, RESERVATION_ANIMATION_LENGTH);
        reservation.update(secondsPassed);
        flip.update(secondsPassed);
    }

    @Override
    public double getRotation() {
        return super.getRotation() + reservation.getValue() * PI / 2;
    }

    @Override
    public double getPerspectiveX() {
        return flip.getValue();
    }

    @Override
    public void moveTo(int x, int y, double duration) {
        super.moveTo(x, y, duration);
    }

    @Override
    public Operator getOperator() {
        return new CardOperator(this);
    }

    @Override
    public int getXX() {
        return x;
    }

    @Override
    public int getYY() {
        return y;
    }
}
