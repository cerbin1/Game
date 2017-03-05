package app.game.card;

import app.util.Random;

public class CardFactory {
    private final CardCostGenerator cost;
    private final CardPointsGenerator points;
    private final CardColorGenerator color;

    public CardFactory() {
        this(new Random());
    }

    public CardFactory(Random random) {
        cost = new CardCostGenerator(random);
        points = new CardPointsGenerator(random);
        color = new CardColorGenerator(random);
    }

    public Card createCheapCard() {
        return new CheapCard(cost.getCheap(), points.generateCheapCardPoints(), color.generateColor());
    }

    public Card createMediumCard() {
        return new MediumCard(cost.getMedium(), points.generateMediumCardPoints(), color.generateColor());
    }

    public Card createExpensiveCard() {
        return new ExpensiveCard(cost.getExpensive(), points.generateExpensiveCardPoints(), color.generateColor());
    }
}
