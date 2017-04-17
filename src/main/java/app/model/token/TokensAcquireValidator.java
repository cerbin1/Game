package app.model.token;

import java.util.function.BiConsumer;

class TokensAcquireValidator {
    private final TokensAmount amount;

    TokensAcquireValidator(TokensAmount amount) {
        this.amount = amount;
    }

    boolean canAcquire(TokensAmount requested) {
        if (requested.getVersatile() != 0) {
            return false;
        }

        for (TokenColor color : TokenColor.values()) {
            if (requested.get(color) > amount.get(color)) {
                return false;
            }
            if (requested.get(color) == 2 && amount.get(color) < 3) {
                return false;
            }
        }

        return isValidationPassed(requested) && !isTooManyTokensChose(requested);
    }

    private boolean isValidationPassed(TokensAmount requested) {
        return validate(requested, 1, 3) || validate(requested, 2, 2);
    }

    private boolean isTooManyTokensChose(TokensAmount requested) {
        return requested.asMap().entrySet().stream().anyMatch(token -> token.getValue() > 2);
    }

    private boolean validate(TokensAmount requestedTokensAmount, int value, int amount) {
        RequestedTokensValidator validator = new RequestedTokensValidator(value, amount);
        requestedTokensAmount.asMap().forEach(validator);
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
