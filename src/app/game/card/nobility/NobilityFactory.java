package app.game.card.nobility;

import app.util.Random;

public class NobilityFactory {

    private Random random;

    public NobilityFactory() {
        this(new Random());
    }

    public NobilityFactory(Random random) {
        this.random = random;
    }

    ConditionsEmploymentNobility conditions = new ConditionsEmploymentNobility();

    public Nobility create() {
        return new Nobility(conditions.getConditions(), generateRandomPoints());
    }

    public int generateRandomPoints() {
        return random.nextInt(3, 4);
    }
}
