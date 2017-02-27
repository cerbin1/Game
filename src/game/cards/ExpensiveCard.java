package game.cards;

import game.Tokens;

public class ExpensiveCard extends Card {
    Tokens cost = new CardCostGenerator().getExpensive();

    public Tokens getCost() {
        return cost;
    }
}
