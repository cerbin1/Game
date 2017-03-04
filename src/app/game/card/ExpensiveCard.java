package app.game.card;

import app.game.token.TokenColor;
import app.game.token.Tokens;

public class ExpensiveCard extends Card {
    public ExpensiveCard() {
        super(new Tokens(), 0, TokenColor.Green);
    }

    public ExpensiveCard(Tokens cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
