package app.model.card;

import app.model.token.TokenColor;
import app.model.token.Tokens;

import static app.model.token.TokenColor.Green;

public class MediumCard extends Card {
    public MediumCard() {
        super(new Tokens(), 0, Green);
    }

    public MediumCard(Tokens cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
