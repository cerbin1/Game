package app.model.token;

import app.model.util.Probability;

public enum TokenColor {
    Green, Purple, Blue, Black, Red;

    public static TokenColor getRandom(Probability probability) {
        int randomColorIndex = probability.nextInt(0, values().length);
        return values()[randomColorIndex];
    }
}
