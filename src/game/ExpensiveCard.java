package game;

public class ExpensiveCard extends Card {
    Tokens cost = new CardCostGenerator().getExpensive();

    public Tokens getCost() {
        return cost;
    }
}
