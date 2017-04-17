package app.model.card;

import app.model.token.TokensAmount;
import app.model.util.Probability;

public class CardCost {
    private final Probability probability;

    CardCost() {
        this(new Probability());
    }

    public CardCost(Probability probability) {
        this.probability = probability;
    }

    public TokensAmount getCheap() {
        return getCost(3, 5);
    }

    public TokensAmount getMedium() {
        return getCost(5, 8);
    }

    public TokensAmount getExpensive() {
        return getCost(7, 14);
    }

    private TokensAmount getCost(int min, int max) {
        int[] cost = {0, 0, 0, 0, 0};
        int tokensCount = probability.nextInt(min, max);
        while (tokensCount-- > 0) {
            cost[probability.nextInt(0, 5)]++;
        }
        return new TokensAmount(cost[0], cost[1], cost[2], cost[3], cost[4]);
    }
}
