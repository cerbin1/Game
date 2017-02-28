package game.cards;

import game.Tokens;

public class Card {
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
}
