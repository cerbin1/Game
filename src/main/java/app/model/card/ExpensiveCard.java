package app.model.card;

import app.model.token.TokenColor;
import app.model.token.Tokens;

public class ExpensiveCard extends Card {
    public ExpensiveCard() {
        super(new Tokens(), 0, TokenColor.Green);
    }

    public ExpensiveCard(Tokens cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
