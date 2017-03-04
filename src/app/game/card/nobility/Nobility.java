package app.game.card.nobility;

import app.game.token.Tokens;

public class Nobility {
    private final Tokens condition;
    private final int points;

    public Nobility() {
        this(new Tokens(), 0);
    }

    public Nobility(Tokens condition, int points) {
        this.condition = condition;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public Tokens getCondition() {
        return condition;
    }
}
