package app.model.card.nobility;

import app.model.token.Tokens;
import app.util.Probability;

public class NobilityFactory {
    private final Probability probability;

    public NobilityFactory() {
        this(new Probability());
    }

    public NobilityFactory(Probability probability) {
        this.probability = probability;
    }

    public Nobility create() {
        return new Nobility(getRandomConditions(), getRandomPoints());
    }

    private Tokens getRandomConditions() {
        return new ConditionsEmploymentNobility(probability).getRandomConditions();
    }

    private int getRandomPoints() {
        return probability.nextInt(3, 4);
    }
}
