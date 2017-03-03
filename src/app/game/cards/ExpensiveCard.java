package app.game.cards;

import app.game.Tokens;

public class ExpensiveCard extends Card {

    public ExpensiveCard() {
        this(new CardCostGenerator().getExpensive());
    }

    public ExpensiveCard(Tokens cost) {
        super(cost, 0);
    }
}
