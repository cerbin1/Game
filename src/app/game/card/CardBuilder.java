package app.game.card;

import app.util.Random;

public class CardBuilder {
    private final Random random;
    private final CardCostGenerator costGenerator;
    private final CardPointsGenerator pointsGenerator;
    private final CardColorGenerator colorGenerator;

    public CardBuilder() {
        this(new Random());
    }

    public CardBuilder(Random random) {
        this.random = random;
        pointsGenerator = new CardPointsGenerator(this.random);
        colorGenerator = new CardColorGenerator(this.random);
        costGenerator = new CardCostGenerator(this.random);
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

