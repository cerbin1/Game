package game;

import java.util.Random;

public class CharacterCard {
    private int points;
    private static int index;
    Tokens tokens = new Tokens(7, 5);

    public CharacterCard() {
        points = new Random().nextInt(2) + 3;
    }
}
