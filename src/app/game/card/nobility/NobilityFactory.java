package app.game.card.nobility;

import app.util.Random;

public class NobilityFactory {
    private final Random random;
    private final ConditionsEmploymentNobility conditions = new ConditionsEmploymentNobility();

    public NobilityFactory() {
        this(new Random());
    }

    public NobilityFactory(Random random) {
        this.random = random;
    }

    public Nobility create() {
        return new Nobility(conditions.getRandomConditions(), generateRandomPoints());
    }

    public int generateRandomPoints() {
        return random.nextInt(3, 4);
    }
}
