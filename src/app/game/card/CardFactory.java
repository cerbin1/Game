package app.game.card;

import app.game.token.TokenColor;
import app.util.Probability;

public class CardFactory {
    private final CardCost cost;
    private final CardPoints points;

    public CardFactory() {
        this(new Probability());
    }

    public CardFactory(Probability probability) {
        cost = new CardCost(probability);
        points = new CardPoints(probability);
    }

    public Card createCheapCard() {
        return new CheapCard(cost.getCheap(), points.getCheap(), TokenColor.getRandom());
    }

    public Card createMediumCard() {
        return new MediumCard(cost.getMedium(), points.getMedium(), TokenColor.getRandom());
    }

    public Card createExpensiveCard() {
        return new ExpensiveCard(cost.getExpensive(), points.getExpensive(), TokenColor.getRandom());
    }
}
