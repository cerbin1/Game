package app.game.token;

import java.util.function.BiConsumer;

public class TokensAcquireValidator {
    private final Tokens tokens;

    public TokensAcquireValidator(Tokens tokens) {
        this.tokens = tokens;
    }

    public boolean canAcquire(Tokens requested) {
        if (requested.getVersatile() != 0) {
            return false;
        }

        for (TokenColor color : TokenColor.values()) {
            if (requested.get(color) > tokens.get(color)) {
                return false;
            }
            if (requested.get(color) == 2 && tokens.get(color) < 3) {
                return false;
            }
        }

        if (!validate(requested, 1, 3) && !validate(requested, 2, 2)) {
            return false;
        }

        if (requested.getGreen() > 2 || requested.getPurple() > 2 || requested.getBlue() > 2 || requested.getBlack() > 2 || requested.getRed() > 2) {
            return false;
        }

        return true;
    }

    private boolean validate(Tokens requestedTokens, int value, int amount) {
        RequestedTokensValidator validator = new RequestedTokensValidator(value, amount);
        requestedTokens.asMap().forEach(validator);
        return validator.isValid();
    }

    private class RequestedTokensValidator implements BiConsumer<TokenColor, Integer> {
        private final int value, expectedAmount;
        private int currentAmount = 0;
        private boolean isPassedValidation = true;

        RequestedTokensValidator(int value, int amount) {
            this.value = value;
            this.expectedAmount = amount;
        }

        @Override
        public void accept(TokenColor tokenColor, Integer integer) {
            if (integer == value || integer == 0) {
                currentAmount += integer;
            } else {
                isPassedValidation = false;
            }
        }

        boolean isValid() {
            return currentAmount == expectedAmount && isPassedValidation;
        }
    }
}
