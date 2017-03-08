package app.game.card;

import app.game.token.TokenColor;
import app.util.Probability;

public class CardFactory {
    private final CardCost cost;
    private final CardPoints points;
    private Probability probability;

    public CardFactory() {
        this(new Probability());
    }

    public CardFactory(Probability probability) {
        this.probability = probability;
        cost = new CardCost(probability);
        points = new CardPoints(probability);
    }

    public Card createCheapCard() {
        return new CheapCard(cost.getCheap(), points.getCheap(), TokenColor.getRandom(probability));
    }

    public Card createMediumCard() {
        return new MediumCard(cost.getMedium(), points.getMedium(), TokenColor.getRandom(probability));
    }

    public Card createExpensiveCard() {
        return new ExpensiveCard(cost.getExpensive(), points.getExpensive(), TokenColor.getRandom(probability));
    }
}
