package app.game.card;

import app.game.TokenColor;

import java.util.Random;

public class CardColorGenerator {
    private final Random random;

    public CardColorGenerator() {
        this(new Random());
    }

    public CardColorGenerator(Random random) {
        this.random = random;
    }

    public TokenColor generateColor() {
        TokenColor[] tokenColors = TokenColor.values();
        return tokenColors[random.nextInt(tokenColors.length)];
    }
}
