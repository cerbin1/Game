package app.game.card.nobility;

import app.game.Tokens;

import java.util.Random;

public class Nobility {
    private final Random random;
    private final Tokens cost;
    private final int points;

    public Nobility() {
        this(new Random());
    }

    public Nobility(Random random) {
        this.random = random;
        this.cost = new ConditionsEmploymentNobility().getConditions();
        this.points = getRandomPoints();
    }

    public Nobility(Tokens cost, int points) {
        this.random = new Random();
        this.cost = cost;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public Tokens getCost() {
        return cost;
    }

    public int getRandomPoints() {
        return random.nextInt(2) + 2;
    }
}
