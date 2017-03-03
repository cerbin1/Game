package app.game.card;

import java.util.Random;

public class CardPointsGenerator {
    private Random random;
    private final int[] cheapCardPoints = {0, 1};
    private final int[] mediumCardPoints = {1, 2, 3};
    private final int[] expensiveCardPoints = {3, 4, 5};

    public CardPointsGenerator() {
        this(new Random());
    }

    public CardPointsGenerator(Random random) {
        this.random = random;
    }

    public int generateCheapCardPoints() {
        return cheapCardPoints[random.nextInt(cheapCardPoints.length)];
    }

    public int generateMediumCardPoints() {
        return mediumCardPoints[random.nextInt(mediumCardPoints.length)];
    }

    public int generateExpensiveCardPoints() {
        return expensiveCardPoints[random.nextInt(expensiveCardPoints.length)];
    }
}
