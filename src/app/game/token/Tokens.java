package app.game.token;

import java.util.EnumMap;
import java.util.Map;

import static app.game.token.TokenColor.*;

public class Tokens {
    private final Map<TokenColor, Integer> tokens = new EnumMap<>(TokenColor.class);
    private final int versatile;

    public Tokens() {
        this(0, 0);
    }

    public Tokens(Tokens tokens) {
        this.tokens.putAll(tokens.asMap());
        this.versatile = tokens.versatile;
    }

    public Tokens(int versatile) {
        this.versatile = versatile;
    }

    public Tokens(int regular, int versatile) {
        tokens.put(Green, regular);
        tokens.put(Purple, regular);
        tokens.put(Blue, regular);
        tokens.put(Black, regular);
        tokens.put(Red, regular);
        this.versatile = versatile;
    }

    public Tokens(int green, int purple, int blue, int black, int red) {
        tokens.put(Green, green);
        tokens.put(Purple, purple);
        tokens.put(Blue, blue);
        tokens.put(Black, black);
        tokens.put(Red, red);
        versatile = 0;
    }

    public Tokens(int green, int purple, int blue, int black, int red, int versatile) {
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

    public int getGreen() {
        return tokens.get(Green);
    }

    public int getPurple() {
        return tokens.get(Purple);
    }

    public int getBlue() {
        return tokens.get(Blue);
    }

    public int getBlack() {
        return tokens.get(Black);
    }

    public int getRed() {
        return tokens.get(Red);
    }

    public int getVersatile() {
        return versatile;
    }

    public Map<TokenColor, Integer> asMap() {
        return tokens;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;

        if (o instanceof Tokens) {
            Tokens that = (Tokens) o;
            return tokens.equals(that.tokens) && versatile == that.getVersatile();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Tokens{" + tokens + ", versatile=" + versatile + '}';
    }

    public Tokens add(Tokens parameter) {
        Tokens result = new Tokens(0, versatile + parameter.versatile);
        for (TokenColor color : TokenColor.values()) {
            result.tokens.put(color, tokens.get(color) + parameter.tokens.get(color));
        }
        return result;
    }

    public Tokens subtract(Tokens parameter) {
        Tokens result = new Tokens(0, versatile - parameter.versatile);
        for (TokenColor color : TokenColor.values()) {
            result.tokens.put(color, get(color) - parameter.get(color));
        }
        return result;
    }
}
