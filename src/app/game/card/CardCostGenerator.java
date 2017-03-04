package app.game.card;

import app.util.Random;

public class CardCostGenerator {
    private final Random random;

    CardCostGenerator() {
        this(new Random());
    }

    public CardCostGenerator(Random random) {
        this.random = random;
    }
}
