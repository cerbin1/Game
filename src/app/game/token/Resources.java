package app.game.token;

import java.util.Map.Entry;
import java.util.function.Consumer;

import static java.lang.Math.abs;
import static java.lang.Math.min;

class Resources {
    private final Tokens stationary, temporary;

    Resources(Tokens stationary, Tokens temporary) {
        this.stationary = stationary.asCost();
        this.temporary = temporary.asCost();
    }

    boolean canBuy(Tokens cost) {
        return calculateChange(cost).asMap().entrySet().stream()
                .allMatch(entry -> entry.getValue() >= 0);
    }

    Tokens calculateChange(Tokens cost) {
        Tokens paidCost = stationary.add(temporary).subtract(cost);
        return compensateInsufficientTokens(paidCost);
    }

    private Tokens compensateInsufficientTokens(Tokens paidCost) {
        InsufficientTokens compensator = new InsufficientTokens(temporary.getVersatile());
        paidCost.asMap().entrySet().forEach(compensator);
        return compensator.getTokensLeft();
    }

    private class InsufficientTokens implements Consumer<Entry<TokenColor, Integer>> {
        private final Tokens change = new Tokens();
        private int versatile;

        InsufficientTokens(int versatile) {
            this.versatile = versatile;
        }

        @Override
        public void accept(Entry<TokenColor, Integer> entry) {
            int consumedVersatile = consumeVersatile(entry.getValue());
            versatile -= consumedVersatile;
            change.asMap().put(entry.getKey(), entry.getValue() + consumedVersatile);
        }

        private int consumeVersatile(Integer entry) {
            if (entry < 0) {
                return min(versatile, abs(entry));
            }
            return 0;
        }

        Tokens getTokensLeft() {
            return new Tokens(change, versatile);
        }
    }
}
