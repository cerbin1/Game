package app.game.card;

import app.game.Tokens;

import java.util.Random;

public class CardCostGenerator {
    private final int[][] cheapCardCostTypes = {{3}, {4}, {2, 2}, {2, 1}, {3, 1, 1}, {2, 2, 1}, {2, 1, 1, 1}, {1, 1, 1, 2}, {1, 1, 1, 1}};
    private final int[][] mediumCardCostTypes = {{5}, {6}, {4, 7}, {3, 2}, {4, 4}, {3, 2, 3}, {2, 1, 2}, {1, 4, 2}, {3, 1, 2}, {1, 2, 4, 1}, {1, 1, 1, 3}, {1, 2, 2, 1}, {1, 1, 2, 1, 1}};
    private final int[][] expensiveCardCostTypes = {{7}, {8}, {3, 4}, {6, 2}, {9, 4}, {3, 3, 3}, {4, 2, 6}, {2, 5, 2}, {1, 7, 2}, {2, 2, 2, 2}, {2, 4, 6, 1}, {4, 2, 1, 5}, {2, 6, 3, 1}, {1, 1, 5, 2, 1}, {2, 3, 4, 3, 1}, {2, 2, 2, 2, 2}, {5, 1, 1, 1, 6}};
    private final Random random;
    private int[] typeCost;
    private int[] cardCost = new int[5];
    private boolean[] isCostAssigned = new boolean[5];

    CardCostGenerator() {
        this(new Random());
    }

    public CardCostGenerator(Random random) {
        this.random = random;
    }

    public Tokens getCheap() {
        typeCost = getRandomCheapCostType();
        for (int i = 0; i < typeCost.length; i++) {
            setSingleCost(i);
        }

        return getTokensWithSetCost();
    }

    public Tokens getMedium() {
        typeCost = getRandomMediumCostType();
        for (int i = 0; i < typeCost.length; i++) {
            setSingleCost(i);
        }
        return getTokensWithSetCost();
    }

    public Tokens getExpensive() {
        typeCost = getRandomExpensiveCostType();
        for (int i = 0; i < typeCost.length; i++) {
            setSingleCost(i);
        }
        return getTokensWithSetCost();
    }

    private void setSingleCost(int i) {
        while (true) {
            int randomToken = getRandomToken(5);
            if (!isCostAssigned[randomToken]) {
                assignCost(i, randomToken);
                break;
            }
        }
    }

    private int getRandomToken(int range) {
        return random.nextInt(range);
    }

    private void assignCost(int i, int randomToken) {
        cardCost[randomToken] = typeCost[i];
        isCostAssigned[randomToken] = true;
    }

    private Tokens getTokensWithSetCost() {
        return new Tokens(cardCost[0], cardCost[1], cardCost[2], cardCost[3], cardCost[4]);
    }

    private int[] getRandomCheapCostType() {
        return cheapCardCostTypes[random.nextInt(cheapCardCostTypes.length)];
    }

    private int[] getRandomMediumCostType() {
        return mediumCardCostTypes[random.nextInt(mediumCardCostTypes.length)];
    }

    private int[] getRandomExpensiveCostType() {
        return expensiveCardCostTypes[random.nextInt(expensiveCardCostTypes.length)];
    }
}
