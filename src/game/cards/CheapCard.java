package game.cards;

import game.Tokens;

public class CheapCard extends Card {
    Tokens cost = new CardCostGenerator().getCheap();

    public Tokens getCost() {
        return cost;
    }
}
