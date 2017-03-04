package app.game.card.nobility;

import app.game.token.Tokens;

import app.util.Random;

public class ConditionsEmploymentNobility {
    private int[] tokenValue = new int[5];
    private boolean[] isValueAssigned = new boolean[5];
    private final Random random;

    ConditionsEmploymentNobility() {
        this(new Random());
    }

    public ConditionsEmploymentNobility(Random random) {
        this.random = random;
    }

    public Tokens getConditions() {
        return getRandomConditions();
    }

    private Tokens getRandomConditions() {
        return isFirstTypeConditionsDrawn() ? getFirstConditionsType() : getSecondConditionsType();
    }

    private boolean isFirstTypeConditionsDrawn() {
        return random.nextInt(0, 1) % 2 == 0;
    }

    private Tokens getFirstConditionsType() {
        for (int i = 0; i < 3; i++) {
            setSingleToken(3);
        }
        return getAssignedTokens();
    }

    private Tokens getSecondConditionsType() {
        for (int i = 0; i < 2; i++) {
            setSingleToken(4);
        }
        return getAssignedTokens();
    }

    private void setSingleToken(int value) {
        while (true) {
            int randomToken = getRandomToken(0, 4);
            if (!isValueAssigned[randomToken]) {
                assignValue(randomToken, value);
                break;
            }
        }
    }

    private int getRandomToken(int min, int max) {
        return random.nextInt(min, max);
    }

    private void assignValue(int randomToken, int value) {
        tokenValue[randomToken] = value;
        isValueAssigned[randomToken] = true;
    }

    private Tokens getAssignedTokens() {
        return new Tokens(tokenValue[0], tokenValue[1], tokenValue[2], tokenValue[3], tokenValue[4]);
    }
}
