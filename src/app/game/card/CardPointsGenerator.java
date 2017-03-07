package app.game.card;

import app.util.Probability;

public class CardPointsGenerator {
    private final Probability probability;

    public CardPointsGenerator() {
        this(new Probability());
    }

    public CardPointsGenerator(Probability probability) {
        this.probability = probability;
    }

    public int generateCheapCardPoints() {
        return probability.nextInt(0, 1);
    }

    public int generateMediumCardPoints() {
        return probability.nextInt(1, 3);
    }

    public int generateExpensiveCardPoints() {
        return probability.nextInt(3, 5);
    }
}
