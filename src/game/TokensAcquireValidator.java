package game;

import java.util.function.BiConsumer;

public class TokensAcquireValidator {
    private final Tokens tokens;

    public TokensAcquireValidator(Tokens tokens) {
        this.tokens = tokens;
    }

    public boolean canAcquire(Tokens requestedTokens) {
        if (requestedTokens.getVersatile() != 0) {
            return false;
        }

        if (tokens.getGreen() < requestedTokens.getGreen()
                || tokens.getPurple() < requestedTokens.getPurple()
                || tokens.getBlue() < requestedTokens.getBlue()
                || tokens.getBlack() < requestedTokens.getBlack()
                || tokens.getRed() < requestedTokens.getRed()) {
            return false;
        }

        if (tokens.getGreen() < 3 && requestedTokens.getGreen() == 2
                || tokens.getPurple() < 3 && requestedTokens.getPurple() == 2
                || tokens.getBlue() < 3 && requestedTokens.getBlue() == 2
                || tokens.getBlack() < 3 && requestedTokens.getBlack() == 2
                || tokens.getRed() < 3 && requestedTokens.getRed() == 2) {
            return false;
        }

        if (!validate(requestedTokens, 1, 3) && !validate(requestedTokens, 2, 2)) {
            return false;
        }

        if (requestedTokens.getGreen() > 2 || requestedTokens.getPurple() > 2 || requestedTokens.getBlue() > 2 || requestedTokens.getBlack() > 2 || requestedTokens.getRed() > 2) {
            return false;
        }

        return true;
    }

    private boolean validate(Tokens requestedTokens, int value, int amount) {
        TokensValidator validator = new TokensValidator(value, amount);
        requestedTokens.asMap().forEach(validator);
        return validator.isRequestedTwoCommonTokens();
    }

    private class TokensValidator implements BiConsumer<TokenColor, Integer> {
        private final int value, expectedAmount;
        private int currentAmount = 0;

        TokensValidator(int value, int amount) {
            this.value = value;
            this.expectedAmount = amount;
        }

        @Override
        public void accept(TokenColor tokenColor, Integer integer) {
            if (integer == value || integer == 0) {
                currentAmount += integer;
            } else {
                currentAmount -= 10;
            }
        }

        boolean isRequestedTwoCommonTokens() {
            return currentAmount == expectedAmount;
        }
    }
}
