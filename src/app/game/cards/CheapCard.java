package app.game.cards;

import app.game.Tokens;

public class CheapCard extends Card {
    public CheapCard() {
        this(new CardCostGenerator().getCheap(), 0);
    }

    public CheapCard(Tokens cost) {
        this(cost, 0);
    }

    private CheapCard(Tokens cost, int points) {
        super(cost, points);
    }
}
