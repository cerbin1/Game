package game.cards;

import game.Tokens;

public class CheapCard extends Card {
    private final Tokens cost;

    public CheapCard() {
        this(new CardCostGenerator().getCheap());
    }

    public CheapCard(Tokens cost) {
        this.cost = cost;
    }

    public Tokens getCost() {
        return cost;
    }
}
