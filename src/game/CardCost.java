package game;

import java.util.Random;

public class CardCost {
    private final int[][] cheapCardCostTypes = {{3}, {4}, {2, 2}, {2, 1}, {3, 1, 1}, {2, 2, 1}, {2, 1, 1, 1}, {1, 1, 1, 2}, {1, 1, 1, 1}};
    private Random random;
    private int[] typeCost;
    private int[] cardCost = new int[5];
    private boolean[] isCostAssigned = new boolean[5];

    public CardCost() {
        this(new Random());
    }

    public CardCost(Random random) {
        this.random = random;
    }

    public Tokens getRandomCheapCardCost() {
        typeCost = getRandomType();
        for (int i = 0; i < typeCost.length; i++) {
            setSingleCost(i);
        }

        return new Tokens(cardCost[0], cardCost[1], cardCost[2], cardCost[3], cardCost[4]);
    }

    private void setSingleCost(int i) {
        while (true) {
            int randomToken = getRandomToken(5);
            if (!isCostAssigned[randomToken]) {
                cardCost[randomToken] = typeCost[i];
                isCostAssigned[randomToken] = true;
                break;
            }
        }
    }

    private int getRandomToken(int range) {
        return random.nextInt(range);
    }

    private int[] getRandomType() {
        return cheapCardCostTypes[random.nextInt(cheapCardCostTypes.length)];
    }

    private void displayCost(Tokens tokens) {
        System.out.println(tokens.getGreen());
        System.out.println(tokens.getWhite());
        System.out.println(tokens.getBlue());
        System.out.println(tokens.getBlack());
        System.out.println(tokens.getRed());
    }

    public static void main(String[] args) {
        CardCost cardCost = new CardCost();
        cardCost.displayCost(cardCost.getRandomCheapCardCost());
    }
}
