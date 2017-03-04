package app.game.card.nobility;

import app.game.Tokens;

import java.util.Random;

public class Nobility {
    private final Random random;
    private final Tokens conditions;
    private final int points;
    private final ConditionsEmploymentNobility conditionsEmploymentNobility = new ConditionsEmploymentNobility();

    public Nobility() {
        this(new Random());
    }

    public Nobility(Random random) {
        this.random = random;
        this.conditions = conditionsEmploymentNobility.getConditions();
        this.points = getRandomPoints();

    }

    public int getPoints() {
        return points;
    }

    public Tokens getConditions() {
        return conditions;
    }

    public Nobility createNobility() {
        return new Nobility();
    }

    public int getRandomPoints() {
        return isFirstTypeOfPointsDrawn() ? 3 : 4;
    }

    private boolean isFirstTypeOfPointsDrawn() {
        return random.nextInt(2) % 2 == 0;
    }
}
