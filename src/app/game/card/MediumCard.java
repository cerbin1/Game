package app.game.card;

import app.game.TokenColor;
import app.game.Tokens;

public class MediumCard extends Card {
    public MediumCard() {
        super(new Tokens(), 0, TokenColor.Green);
    }

    public MediumCard(Tokens cost, int points, TokenColor color) {
        super(cost, points, color);
    }
}
