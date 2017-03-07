package app.game.card.nobility;

import app.util.Random;

public class NobilityFactory {
    private final Random random;

    public NobilityFactory() {
        this(new Random());
    }

    public NobilityFactory(Random random) {
        this.random = random;
    }

    public Nobility create() {
        return new Nobility(new ConditionsEmploymentNobility(random).getRandomConditions(), generateRandomPoints());
    }

    private int generateRandomPoints() {
        return random.nextInt(3, 4);
    }
}
