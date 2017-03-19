package app.model.card;

import app.model.token.Tokens;

public abstract class Figure {
    private Tokens cost;
    private int points;

    public Figure(Tokens cost, int points) {
        this.cost = cost;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public Tokens getCost() {
        return cost;
    }
}
