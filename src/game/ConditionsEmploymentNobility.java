package game;

import java.util.Random;

public class ConditionsEmploymentNobility {
    private int[] cardCost = new int[5];
    private boolean[] isCostAssigned = new boolean[5];
    private Random random;

    private ConditionsEmploymentNobility() {
        random = new Random();
        randomCardCost();
    }

    public ConditionsEmploymentNobility(Random random) {
        this();
        this.random = random;
    }

    public Tokens getRandomCost() {
        randomCardCost();
        return new Tokens(cardCost[0], cardCost[1], cardCost[2], cardCost[3], cardCost[4]);
    }

    private void randomCardCost() {
        if (firstCostType()) {
            setFirstCostType();
        } else {
            setSecondCostType();
        }
    }

    private boolean firstCostType() {
        return (random.nextInt(2)) % 2 == 0;
    }

    private void setFirstCostType() {
        for (int i = 0; i < 3; i++) {
            setSingleCost(i);
        }
    }

    private void setSingleCost(int i) {
        while (true) {
            int randomToken = getRandomToken(5);
            if (!isCostAssigned[randomToken]) {
                assignCost(randomToken);
                break;
            }
        }
    }

    private int getRandomToken(int range) {
        return random.nextInt(range);
    }


    private void assignCost(int randomToken) {
        cardCost[randomToken] = 3;
        isCostAssigned[randomToken] = true;
    }

    private void setSecondCostType() {
        for (int i = 0; i < 2; i++) {
            setSingleCost(i);
        }
    }
}
