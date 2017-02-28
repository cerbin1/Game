package game.cards;

import game.Tokens;

public class MediumCard extends Card {

    public MediumCard() {
        this(new CardCostGenerator().getMedium());
    }

    public MediumCard(Tokens cost) {
        super(cost, 0);
    }
}
