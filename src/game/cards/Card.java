package game.cards;

public class Card {
    private int points;
    private boolean reserved;

    public int getPoints() {
        return points;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean value) {
        reserved = value;
    }
}
