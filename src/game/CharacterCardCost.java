package game;

import java.util.Random;

public class CharacterCardCost {
    private int green, white, blue, black, red;
    private boolean[] isCostSet = new boolean[5];

    public CharacterCardCost() {
        if (new Random().nextInt(3) + 1 % 2 == 0) {
            for (int i = 0; i < 3; i++) {
                white(true) {
                    if(!isCostSet[i]) {

                    }
                }
            }
        }
    }
}
