package app.game.card;

import app.util.Probability;

public class CardFactory {
    private final CardCostGenerator cost;
    private final CardPointsGenerator points;
    private final CardColorGenerator color;

    public CardFactory() {
        this(new Probability());
    }

    public CardFactory(Probability probability) {
        cost = new CardCostGenerator(probability);
        points = new CardPointsGenerator(probability);
        color = new CardColorGenerator(probability);
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
