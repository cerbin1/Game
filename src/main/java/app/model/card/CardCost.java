package app.model.card;

import app.model.token.Tokens;
import app.model.util.Probability;

public class CardCost {
    private final Probability probability;

    CardCost() {
        this(new Probability());
    }

    public CardCost(Probability probability) {
        this.probability = probability;
    }

    public Tokens getCheap() {
        return getCost(3, 5);
    }

    public Tokens getMedium() {
        return getCost(5, 8);
    }

    public Tokens getExpensive() {
        return getCost(7, 14);
    }

    private Tokens getCost(int min, int max) {
        int[] cost = {0, 0, 0, 0, 0};
        int tokensCount = probability.nextInt(min, max);
        while (tokensCount-- > 0) {
            cost[probability.nextInt(0, 5)]++;
        }
        return new Tokens(cost[0], cost[1], cost[2], cost[3], cost[4]);
    }
}
