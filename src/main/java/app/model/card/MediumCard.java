package app.model.card;

import app.model.token.TokenColor;
import app.model.token.Tokens;

public class MediumCard extends Card {
    public MediumCard() {
        super(new Tokens(), 0, TokenColor.Green);
    }

    public MediumCard(Tokens cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
