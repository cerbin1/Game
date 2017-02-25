package game;

import java.util.Random;

class CardCost {
    private int[] cost = new int[5];
    private Tokens tokens;

    CardCost() {
        randomCardCost();
        tokens = setTokens();
    }

    private Tokens setTokens() {
        return new Tokens(cost[0], cost[1], cost[2], cost[3], cost[4]);
    }

    private void randomCardCost() {
        if (firstCostType()) {
            setFirstCostType();
        } else {
            setSecondCostType();
        }
    }

    private void setSecondCostType() {
        cost[3] = 4;
        cost[4] = 4;
    }

    private void setFirstCostType() {
        cost[0] = 3;
        cost[1] = 3;
        cost[2] = 3;
    }

    private boolean firstCostType() {
        return (new Random().nextInt(3) + 1) % 2 == 0;
    }
}
