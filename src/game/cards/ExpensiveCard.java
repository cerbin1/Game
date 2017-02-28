package game.cards;

import game.Tokens;

public class ExpensiveCard extends Card {
    private final Tokens cost;

    public ExpensiveCard() {
        this(new CardCostGenerator().getExpensive());
    }

    public ExpensiveCard(Tokens cost) {
        this.cost = cost;
    }

    public Tokens getCost() {
        return cost;
    }
}
