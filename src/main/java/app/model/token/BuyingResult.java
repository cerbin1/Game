package app.model.token;

public class BuyingResult {
    private final Tokens remaining, spent;

    public BuyingResult(Tokens remaining, Tokens spent) {
        this.remaining = remaining;
        this.spent = spent;
    }

    public boolean canBuy() {
        return remaining.asMap().entrySet().stream().allMatch(entry -> entry.getValue() >= 0);
    }

    public Tokens getRemaining() {
        return remaining;
    }

    public Tokens getSpent() {
        return spent;
    }
}
