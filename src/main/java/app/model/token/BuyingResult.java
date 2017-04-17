package app.model.token;

public class BuyingResult {
    private final TokensAmount remaining, spent;

    public BuyingResult(TokensAmount remaining, TokensAmount spent) {
        this.remaining = remaining;
        this.spent = spent;
    }

    public boolean canBuy() {
        return remaining.asMap().entrySet().stream().allMatch(entry -> entry.getValue() >= 0);
    }

    public TokensAmount getRemaining() {
        return remaining;
    }

    public TokensAmount getSpent() {
        return spent;
    }
}
