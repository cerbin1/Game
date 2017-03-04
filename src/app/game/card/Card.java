package app.game.card;

import app.game.token.TokenColor;
import app.game.token.Tokens;

public abstract class Card {
    private boolean reserved = false;
    private TokenColor color;
    private Tokens cost;
    private int points;

    Card(Tokens cost, int points, TokenColor color) {
        this.cost = cost;
        this.points = points;
        this.color = color;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Tokens getCost() {
        return cost;
    }

    public int getPoints() {
        return points;
    }

    public TokenColor getColor() {
        return color;
    }
}
