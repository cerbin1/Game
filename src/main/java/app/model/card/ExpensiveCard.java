package app.model.card;

import app.model.token.TokenColor;
import app.model.token.TokensAmount;

import static app.model.token.TokenColor.*;

public class ExpensiveCard extends Card {
    public ExpensiveCard() {
        super(new TokensAmount(), 0, Green);
    }

    public ExpensiveCard(TokensAmount cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
