package app.game.card.nobility;

import app.util.Random;

public class NobilityPointsGenerator {

    private Random random;

    public NobilityPointsGenerator() {
        this(new Random());
    }

    public NobilityPointsGenerator(Random random) {
        this.random = random;
    }

    public int generateRandomPoints() {
        return random.nextInt(3, 4);
    }
}
