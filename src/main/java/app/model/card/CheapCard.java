package app.model.card;

import app.model.token.TokenColor;
import app.model.token.TokensAmount;

import static app.model.token.TokenColor.*;

public class CheapCard extends Card {
    public CheapCard() {
        super(new TokensAmount(), 0, Green);
    }

    public CheapCard(TokensAmount cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
