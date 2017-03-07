package app.game.card.nobility;

import app.game.token.Tokens;
import app.util.Probability;

public class ConditionsEmploymentNobility {
    private int[] tokenValue = new int[5];
    private final Probability probability;

    ConditionsEmploymentNobility() {
        this(new Probability());
    }

    public ConditionsEmploymentNobility(Probability probability) {
        this.probability = probability;
    }

    public Tokens getRandomConditions() {
        return isFirstTypeDrawn() ? getConditions(3, 3) : getConditions(2, 4);
    }

    private boolean isFirstTypeDrawn() {
        return probability.nextInt(0, 1) % 2 == 0;
    }

    private Tokens getConditions(int typesCount, int value) {
        for (int i = 0; i < typesCount; i++) {
            setSingleToken(value);
        }
        return getAssignedTokens();
    }

    private void setSingleToken(int value) {
        while (true) {
            int token = getRandomToken(0, 4);
            if (tokenValue[token] == 0) {
                tokenValue[token] = value;
                break;
            }
        }
    }

    private int getRandomToken(int min, int max) {
        return probability.nextInt(min, max);
    }

    private Tokens getAssignedTokens() {
        return new Tokens(tokenValue[0], tokenValue[1], tokenValue[2], tokenValue[3], tokenValue[4]);
    }
}
