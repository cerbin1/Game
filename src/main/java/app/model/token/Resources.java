package app.model.token;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Resources {
    private final Tokens stationary, temporary;

    public Resources(Tokens stationary, Tokens temporary) {
        this.stationary = stationary.asCost();
        this.temporary = temporary;
    }

    public BuyingResult buy(Tokens cost) {
        Tokens costAfterMines = cost.subtract(stationary);
        Tokens paidCost = temporary.subtract(Tokens.Operations.removeDebts(costAfterMines));
        Tokens remaining = compensateInsufficientTokens(paidCost);
        return new BuyingResult(remaining, temporary.subtract(remaining));
    }

    private Tokens compensateInsufficientTokens(Tokens paidCost) {
        InsufficientTokens compensator = new InsufficientTokens(temporary.getVersatile());
        paidCost.asMap().entrySet().forEach(compensator);
        return compensator.getTokensLeft();
    }

    private class InsufficientTokens implements Consumer<Entry<TokenColor, Integer>> {
        private final Map<TokenColor, Integer> change = new EnumMap<>(TokenColor.class);
        private int versatile;

        InsufficientTokens(int versatile) {
            this.versatile = versatile;
        }

        @Override
        public void accept(Entry<TokenColor, Integer> entry) {
            int consumedVersatile = consumeVersatile(entry.getValue());
            versatile -= consumedVersatile;
            change.put(entry.getKey(), entry.getValue() + consumedVersatile);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resources that = (Resources) o;

        return stationary.equals(that.stationary) && temporary.equals(that.temporary);
    }

    @Override
    public String toString() {
        return "Stationary: " + stationary + ", temporary: " + temporary;
    }
}
