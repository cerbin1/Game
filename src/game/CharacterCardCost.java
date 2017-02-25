package game;

import java.util.Random;

class CharacterCardCost {
    private int[] tokens = new int[5];
    private String[] colors = {"green", "white", "blue", "black", "red"};

    CharacterCardCost() {
        randomCardCost();
        displayCost();
    }

    private void randomCardCost() {
        if (firstCostType()) {
            setFirstCostType();
        } else {
            setSecondCostType();
        }
    }

    private void setSecondCostType() {
        tokens[3] = 4;
        tokens[4] = 4;
    }

    private void setFirstCostType() {
        tokens[0] = 3;
        tokens[1] = 3;
        tokens[2] = 3;
    }

    private boolean firstCostType() {
        return (new Random().nextInt(3) + 1) % 2 == 0;
    }

    void displayCost() {
        System.out.println("Koszt");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(colors[i] + ": " + tokens[i]);
        }
    }
}
