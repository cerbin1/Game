package app.model.card;

import app.model.util.Probability;

public class CardPoints {
    private final Probability probability;

    public CardPoints() {
        this(new Probability());
    }

    public CardPoints(Probability probability) {
        this.probability = probability;
    }

    public int getCheap() {
        return probability.nextInt(0, 1);
    }

    public int getMedium() {
        return probability.nextInt(1, 3);
    }

    public int getExpensive() {
        return probability.nextInt(3, 5);
    }
}
