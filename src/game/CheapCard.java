package game;

public class CheapCard extends Card {
    Tokens cost = new CardCostGenerator().getCheap();

    public Tokens getCost() {
        return cost;
    }
}
