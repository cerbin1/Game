package game;

import java.util.EnumMap;
import java.util.Map;

import static game.TokenColor.*;

public class Tokens {
    private final Map<TokenColor, Integer> tokens = new EnumMap<>(TokenColor.class);
    private final int versatile;

    public Tokens() {
        this(0, 0);
    }

    public Tokens(int commonCount, int rareCount) {
        tokens.put(Green, commonCount);
        tokens.put(Purple, commonCount);
        tokens.put(Blue, commonCount);
        tokens.put(Black, commonCount);
        tokens.put(Red, commonCount);
        versatile = rareCount;
    }

    public Tokens(int green, int purple, int blue, int black, int red) {
        tokens.put(Green, green);
        tokens.put(Purple, purple);
        tokens.put(Blue, blue);
        tokens.put(Black, black);
        tokens.put(Red, red);
        versatile = 0;
    }

    public int get(TokenColor color) {
        return tokens.get(color);
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
}
