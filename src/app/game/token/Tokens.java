package app.game.token;

import java.util.EnumMap;
import java.util.Map;

public class Tokens {
    private final Map<TokenColor, Integer> tokens = new EnumMap<>(TokenColor.class);
    private final int versatile;

    public Tokens() {
        this(0, 0);
    }

    public Tokens(int commonCount, int rareCount) {
        tokens.put(TokenColor.Green, commonCount);
        tokens.put(TokenColor.Purple, commonCount);
        tokens.put(TokenColor.Blue, commonCount);
        tokens.put(TokenColor.Black, commonCount);
        tokens.put(TokenColor.Red, commonCount);
        versatile = rareCount;
    }

    public Tokens(int green, int purple, int blue, int black, int red) {
        tokens.put(TokenColor.Green, green);
        tokens.put(TokenColor.Purple, purple);
        tokens.put(TokenColor.Blue, blue);
        tokens.put(TokenColor.Black, black);
        tokens.put(TokenColor.Red, red);
        versatile = 0;
    }

    public int get(TokenColor color) {
        return tokens.get(color);
    }

    public int getGreen() {
        return tokens.get(TokenColor.Green);
    }

    public int getPurple() {
        return tokens.get(TokenColor.Purple);
    }

    public int getBlue() {
        return tokens.get(TokenColor.Blue);
    }

    public int getBlack() {
        return tokens.get(TokenColor.Black);
    }

    public int getRed() {
        return tokens.get(TokenColor.Red);
    }

    public int getVersatile() {
        return versatile;
    }

    public Map<TokenColor, Integer> asMap() {
        return tokens;
    }
}
