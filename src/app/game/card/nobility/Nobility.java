package app.game.card.nobility;

import app.game.Tokens;

import java.util.Random;

public class Nobility {
    private final Random random;
    private final Tokens conditions;
    private final int points;

    public Nobility() {
        this(new Random());
    }

    public Nobility(Random random) {
        this.random = random;
        this.conditions = new ConditionsEmploymentNobility().getConditions();
        this.points = getRandomPoints();
    }

    public Nobility(Tokens conditions, int points) {
        this.random = new Random();
        this.conditions = conditions;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public Tokens getConditions() {
        return conditions;
    }

    public int getRandomPoints() {
        return random.nextInt(2) + 2;
    }
}
