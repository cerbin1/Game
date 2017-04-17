package app.model.card;

import app.model.token.TokenColor;
import app.model.token.TokensAmount;

import static app.model.token.TokenColor.Green;

public class MediumCard extends Card {
    public MediumCard() {
        super(new TokensAmount(), 0, Green);
    }

    public MediumCard(TokensAmount cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
