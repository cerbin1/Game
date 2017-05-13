package app.model.token;

import app.model.util.Probability;

import java.util.Arrays;
import java.util.stream.Stream;

public enum TokenColor {
    Green, Purple, Blue, Black, Red;

    public static TokenColor getRandom(Probability probability) {
        int randomColorIndex = probability.nextInt(0, values().length);
        return values()[randomColorIndex];
    }

    public static Stream<TokenColor> stream() {
        return Arrays.stream(values());
    }
}
