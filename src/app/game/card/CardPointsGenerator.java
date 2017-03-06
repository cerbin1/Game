package app.game.card;

import app.util.Random;

public class CardPointsGenerator {
    private final Random random;

    public CardPointsGenerator() {
        this(new Random());
    }

    public CardPointsGenerator(Random random) {
        this.random = random;
    }

    public int generateCheapCardPoints() {
        return random.nextInt(0, 1);
    }

    public int generateMediumCardPoints() {
        return random.nextInt(1, 3);
    }

    public int generateExpensiveCardPoints() {
        return random.nextInt(3, 5);
    }
}
