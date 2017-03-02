package game.cards;

import game.TokenColor;
import game.Tokens;

public abstract class Card {
    private boolean reserved = false;
    private Tokens cost;
    private int points;

    public Card(Tokens cost, int points) {
        this.cost = cost;
        this.points = points;
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
        return TokenColor.Black;
    }
}
