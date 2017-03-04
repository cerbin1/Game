package app.game.card;

import app.util.Random;

public class CardBuilder {
    private Random random;
    private final CardCostGenerator costGenerator = new CardCostGenerator(random);
    private final CardPointsGenerator pointsGenerator = new CardPointsGenerator(random);
    private final CardColorGenerator colorGenerator = new CardColorGenerator(random);

    public CardBuilder() {
        this(new Random());
    }

    public CardBuilder(Random random) {
        this.random = random;
    }

    public Card createCheapCard() {
        return new CheapCard(costGenerator.getCheap(), pointsGenerator.generateCheapCardPoints(), colorGenerator.generateColor());
    }

    public Card createMediumCard() {
        return null;
    }

    public Card createExpensiveCard() {
        return null;
    }
}

