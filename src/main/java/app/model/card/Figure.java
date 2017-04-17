package app.model.card;

import app.model.token.TokensAmount;

public abstract class Figure {
    private final TokensAmount cost;
    private final int points;

    public Figure(TokensAmount cost, int points) {
        this.cost = cost;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public TokensAmount getCost() {
        return cost;
    }
}
