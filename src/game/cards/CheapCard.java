package game.cards;

import game.Tokens;

public class CheapCard extends Card {

    public CheapCard() {
        this(new CardCostGenerator().getCheap());
    }

    public CheapCard(Tokens cost) {
        super(cost, 0);
    }
}
