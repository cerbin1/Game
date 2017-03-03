package app.game.card;

import app.game.TokenColor;
import app.game.Tokens;

public class CheapCard extends Card {
    public CheapCard() {
        super(new Tokens(), 0, TokenColor.Green);
    }

    public CheapCard(Tokens cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
