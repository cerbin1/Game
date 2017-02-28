package game.cards;

public class Card {
    private final int points;
    private boolean reserved = false;

    public Card() {
        this(0);
    }

    public Card(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
