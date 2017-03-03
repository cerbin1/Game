package app.game.card;

import java.util.Random;

public class CardPointsGenerator {
    private final Random random;
    private final int[] cheapPoints = {0, 1};
    private final int[] mediumPoints = {1, 2, 3};
    private final int[] expensivePoints = {3, 4, 5};

    public CardPointsGenerator() {
        this(new Random());
    }

    public CardPointsGenerator(Random random) {
        this.random = random;
    }

    public int generateCheapCardPoints() {
        return cheapPoints[random.nextInt(cheapPoints.length)];
    }

    public int generateMediumCardPoints() {
        return mediumPoints[random.nextInt(mediumPoints.length)];
    }

    public int generateExpensiveCardPoints() {
        return expensivePoints[random.nextInt(expensivePoints.length)];
    }
}
