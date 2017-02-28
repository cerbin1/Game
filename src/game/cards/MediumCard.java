package game.cards;

import game.Tokens;

public class MediumCard extends Card {
    private final Tokens cost;

    public MediumCard() {
        this(new CardCostGenerator().getMedium());
    }

    private MediumCard(Tokens cost) {
        this.cost = cost;
    }

    public Tokens getCost() {
        return cost;
    }
}
