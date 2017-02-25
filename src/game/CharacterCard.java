package game;

public class CharacterCard {
    private final int points = 3;
    private static int index;
    Tokens tokens = new Tokens(7, 5);

    public CharacterCard() {

    }

    public int getPoints() {
        return points;
    }
}
