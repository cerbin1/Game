package app.model.token;

import java.util.EnumMap;
import java.util.Map;

import static app.model.token.TokenColor.*;
import static java.lang.Math.max;

public class TokensAmount {
    private final Map<TokenColor, Integer> tokens = new EnumMap<>(TokenColor.class);
    private final int versatile;

    public TokensAmount() {
        this(0, 0);
    }

    public TokensAmount(int versatile) {
        this(0, versatile);
    }

    public TokensAmount(TokensAmount tokensAmount) {
        this.tokens.putAll(tokensAmount.asMap());
        this.versatile = tokensAmount.versatile;
    }

    public TokensAmount(Map<TokenColor, Integer> tokens, int versatile) {
        this.tokens.putAll(tokens);
        this.versatile = versatile;
    }

    public TokensAmount(TokensAmount tokensAmount, int versatile) {
        this.tokens.putAll(tokensAmount.asMap());
        this.versatile = versatile;
    }

    public TokensAmount(int regular, int versatile) {
        tokens.put(Green, regular);
        tokens.put(Purple, regular);
        tokens.put(Blue, regular);
        tokens.put(Black, regular);
        tokens.put(Red, regular);
        this.versatile = versatile;
    }

    public TokensAmount(int green, int purple, int blue, int black, int red) {
        tokens.put(Green, green);
        tokens.put(Purple, purple);
        tokens.put(Blue, blue);
        tokens.put(Black, black);
        tokens.put(Red, red);
        versatile = 0;
    }

    public TokensAmount(int green, int purple, int blue, int black, int red, int versatile) {
        tokens.put(Green, green);
        tokens.put(Purple, purple);
        tokens.put(Blue, blue);
        tokens.put(Black, black);
        tokens.put(Red, red);
        this.versatile = versatile;
    }

    int get(TokenColor color) {
        return tokens.getOrDefault(color, 0);
    }

    public int getVersatile() {
        return versatile;
    }

    public Map<TokenColor, Integer> asMap() {
        Map<TokenColor, Integer> map = new EnumMap<>(TokenColor.class);
        map.putAll(tokens);
        return map;
    }

    TokensAmount asCost() {
        TokensAmount tokensAmount = new TokensAmount(0);
        tokensAmount.tokens.putAll(this.tokens);
        return tokensAmount;
    }

    public TokensAmount add(TokensAmount component) {
        TokensAmount result = new TokensAmount(component, versatile + component.versatile);
        tokens.forEach((color, amount) -> result.tokens.merge(color, amount, Integer::sum));
        return result;
    }

    public TokensAmount subtract(TokensAmount subtrahend) {
        TokensAmount result = new TokensAmount(0, versatile - subtrahend.versatile);
        for (TokenColor color : TokenColor.values()) {
            result.tokens.put(color, get(color) - subtrahend.get(color));
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokensAmount that = (TokensAmount) o;

        return tokens.equals(that.tokens) && versatile == that.getVersatile();
    }

    @Override
    public String toString() {
        return "TokensAmount{" + tokens + ", versatile=" + versatile + '}';
    }

    static class Operations {
        static TokensAmount removeDebts(TokensAmount tokensAmount) {
            Map<TokenColor, Integer> mapCopy = tokensAmount.asMap();
            mapCopy.entrySet().forEach(entry -> entry.setValue(max(0, entry.getValue())));
            return new TokensAmount(mapCopy, tokensAmount.versatile);
        }
    }
}
