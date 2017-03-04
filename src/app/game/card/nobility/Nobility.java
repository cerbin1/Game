package app.game.card.nobility;

import app.game.token.Tokens;

import app.util.Random;

public class Nobility {
    private final Random random;
    private final Tokens condition;
    private final int points;

    public Nobility() {
        this(new Random());
    }

    public Nobility(Random random) {
        this.random = random;
        this.condition = new ConditionsEmploymentNobility().getConditions();
        this.points = getRandomPoints();
    }

    public Nobility(Tokens condition, int points) {
        this.random = new Random();
        this.condition = condition;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public Tokens getCondition() {
        return condition;
    }

    public int getRandomPoints() {
        return random.nextInt(3, 4);
    }
}
