package game.cards;

import game.Tokens;

public class Card {
    private boolean reserved = false;
    private Tokens cost;

    public Card(Tokens cost) {
        this.cost = cost;
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
}
