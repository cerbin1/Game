package app.game.token;

public class BuyingResult {
    private final Tokens remaining;

    public BuyingResult(Tokens remaining) {
        this.remaining = remaining;
    }

    public boolean canBuy() {
        return remaining.asMap().entrySet().stream().allMatch(entry -> entry.getValue() >= 0);
    }

    public Tokens getRemaining() {
        return remaining;
    }
}
