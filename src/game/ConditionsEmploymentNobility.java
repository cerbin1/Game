package game;

import java.util.Random;

public class ConditionsEmploymentNobility {
    private int[] tokenValue = new int[5];
    private boolean[] isValueAssigned = new boolean[5];
    private Random random;

    private ConditionsEmploymentNobility() {
        this(new Random());
    }

    public ConditionsEmploymentNobility(Random random) {
        this.random = random;
    }

    public Tokens getConditions() {
        return getRandomConditions();
    }

    private Tokens getRandomConditions() {
        if (isFirstTypeDrawn()) {
            return getFirstConditionsType();
        } else {
            return getSecondConditionsType();
        }
    }

    private boolean isFirstTypeDrawn() {
        return (random.nextInt(2)) % 2 == 0;
    }

    private Tokens getFirstConditionsType() {
        for (int i = 0; i < 3; i++) {
            setSingleToken(3);
        }
        return getAssignedTokens();
    }

    private Tokens getAssignedTokens() {
        return new Tokens(tokenValue[0], tokenValue[1], tokenValue[2], tokenValue[3], tokenValue[4]);
    }

    private void setSingleToken(int value) {
        while (true) {
            int randomToken = getRandomToken(5);
            if (!isValueAssigned[randomToken]) {
                assignValue(randomToken, value);
                break;
            }
        }
    }

    private int getRandomToken(int range) {
        return random.nextInt(range);
    }

    private void assignValue(int randomToken, int value) {
        tokenValue[randomToken] = value;
        isValueAssigned[randomToken] = true;
    }

    private Tokens getSecondConditionsType() {
        for (int i = 0; i < 2; i++) {
            setSingleToken(4);
        }
        return getAssignedTokens();
    }
}
