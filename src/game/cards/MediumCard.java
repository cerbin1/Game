package game.cards;

import game.Tokens;

public class MediumCard extends Card {
    Tokens cost = new CardCostGenerator().getMedium();

    public Tokens getCost() {
        return cost;
    }
}
