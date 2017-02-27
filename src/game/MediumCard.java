package game;

public class MediumCard extends Card {
    Tokens cost = new CardCostGenerator().getMedium();

    public Tokens getCost() {
        return cost;
    }
}
