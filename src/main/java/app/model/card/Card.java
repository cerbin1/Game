package app.model.card;

import app.model.token.TokenColor;
import app.model.token.Tokens;

public abstract class Card extends Figure{
    private boolean reserved = false;
    private final TokenColor color;

    Card(Tokens cost, int points, TokenColor color) {
        super(cost, points);
        this.color = color;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public TokenColor getColor() {
        return color;
    }

    public boolean is(TokenColor color) {
        return this.color.equals(color);
    }
}
