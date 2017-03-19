package app.model.card;

import app.model.token.TokenColor;
import app.model.token.Tokens;

public class CheapCard extends Card {
    public CheapCard() {
        super(new Tokens(), 0, TokenColor.Green);
    }

    public CheapCard(Tokens cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
