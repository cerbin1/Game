package app.game.card.nobility;

import app.game.token.Tokens;
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
        return new Nobility(getRandomConditions(), getRandomPoints());
    }

    private Tokens getRandomConditions() {
        return new ConditionsEmploymentNobility(random).getRandomConditions();
    }

    private int getRandomPoints() {
        return random.nextInt(3, 4);
    }
}
