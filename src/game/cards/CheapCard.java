package game.cards;

import game.Tokens;

public class CheapCard extends Card {
    public CheapCard() {
        this(new CardCostGenerator().getCheap(), 0);
    }

    public CheapCard(Tokens cost) {
        this(cost, 0);
    }

    public CheapCard(Tokens cost, int points) {
        super(cost, points);
    }
}
