package game;

import java.util.Random;

public class CardCost {
    private Tokens cost;
    private final int[][] typesOfCost = {{3}, {4}, {2, 2}, {2, 1}, {3, 1, 1}, {2, 2, 1}, {2, 1, 1, 1}, {1, 1, 1, 2}, {1, 1, 1, 1}};

    private CardCost() {
        cost = getRandomCheapCardCost();
        System.out.println(cost.getGreen());
        System.out.println(cost.getWhite());
        System.out.println(cost.getBlue());
        System.out.println(cost.getBlack());
        System.out.println(cost.getRed());
    }

    private Tokens getRandomCheapCardCost() {
        int[] typeCost = getRandomType();
        int[] cardCost = new int[5];
        boolean[] isCostAssigned = new boolean[5];
        for (int i = 0; i < typeCost.length; i++) {
            while (true) {
                int randomToken = getRandomToken();
                if (!isCostAssigned[randomToken]) {
                    cardCost[randomToken] = typeCost[i];
                    isCostAssigned[randomToken] = true;
                    break;
                }
            }
        }
        return new Tokens(cardCost[0], cardCost[1], cardCost[2], cardCost[3], cardCost[4]);
    }

    private int getRandomToken() {
        return new Random().nextInt(5);
    }

    private int[] getRandomType() {
        return typesOfCost[new Random().nextInt(typesOfCost.length)];
    }

    public static void main(String[] args) {
        new CardCost();
    }
}
