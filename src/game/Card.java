package game;

public class Card {
    private final int points = 3;
    private static int index;
    Tokens tokens = new Tokens(7, 5);

    public Card() {

    }

    public int getPoints() {
        return points;
    }
}
