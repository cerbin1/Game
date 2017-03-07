package app.game.card;

import app.game.token.TokenColor;
import app.util.Probability;

public class CardColorGenerator {
    private final Probability probability;

    public CardColorGenerator() {
        this(new Probability());
    }

    public CardColorGenerator(Probability probability) {
        this.probability = probability;
    }

    public TokenColor generateColor() {
        TokenColor[] tokenColors = TokenColor.values();
        return tokenColors[probability.nextInt(0, tokenColors.length)];
    }
}
