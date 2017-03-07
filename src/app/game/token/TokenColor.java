package app.game.token;

import app.util.Probability;

public enum TokenColor {
    Green, Purple, Blue, Black, Red;

    public static TokenColor getRandom() {
        return getRandom(new Probability());
    }

    public static TokenColor getRandom(Probability probability) {
        TokenColor[] tokenColors = TokenColor.values();
        return tokenColors[probability.nextInt(0, tokenColors.length)];
    }
}
