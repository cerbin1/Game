package app.game.card;

import app.game.TokenColor;

import java.util.Random;

public class CardColorGenerator {
    private final Random random;
    private TokenColor[] tokenColors = {TokenColor.Green, TokenColor.Purple, TokenColor.Blue, TokenColor.Black, TokenColor.Red};

    public CardColorGenerator() {
        this(new Random());
    }

    public CardColorGenerator(Random random) {
        this.random = random;
    }

    public TokenColor generateColor() {
        return tokenColors[random.nextInt(tokenColors.length)];
    }
}
