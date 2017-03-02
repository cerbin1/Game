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

        if (!requestedThreeSingleTokens(requestedTokens) && !requestedTwoCommonTokens(requestedTokens)) {
            return false;
        }

        return true;
    }

    private boolean requestedThreeSingleTokens(Tokens requestedTokens) {
        SingleTokensValidator validator = new SingleTokensValidator();
        requestedTokens.asMap().forEach(validator);
        return validator.isRequestedThreeSingleTokens();
    }

    private class SingleTokensValidator implements BiConsumer<TokenColor, Integer> {
        private int numberOfOnes = 0;

        @Override
        public void accept(TokenColor tokenColor, Integer integer) {
            if (integer == 1 || integer == 0) {
                numberOfOnes += integer;
            } else {
                throw new RuntimeException();
            }
        }

        boolean isRequestedThreeSingleTokens() {
            return numberOfOnes == 3;
        }
    }

    private boolean requestedTwoCommonTokens(Tokens requestedTokens) {
        TwoCommonTokensValidator validator = new TwoCommonTokensValidator();
        requestedTokens.asMap().forEach(validator);
        return validator.isRequestedTwoCommonTokens();
    }

    private class TwoCommonTokensValidator implements BiConsumer<TokenColor, Integer> {
        private int tokensValue = 0;

        @Override
        public void accept(TokenColor tokenColor, Integer integer) {
            if (integer == 2 || integer == 0) {
                tokensValue += integer;
            } else {
                throw new RuntimeException();
            }
        }

        boolean isRequestedTwoCommonTokens() {
            return tokensValue == 2;
        }
    }
}
