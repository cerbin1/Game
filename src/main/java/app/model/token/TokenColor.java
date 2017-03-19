package app.model.token;

import app.model.util.Probability;

public enum TokenColor {
    Green, Purple, Blue, Black, Red;

    public static TokenColor getRandom(Probability probability) {
        int index = probability.nextInt(0, values().length);
        return values()[index];
    }
}
