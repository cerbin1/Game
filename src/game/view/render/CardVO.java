package game.view.render;

import game.cards.Card;

public class CardVO extends ViewObject {
    private final Card card;

    public CardVO(Card card, int x, int y) {
        super(x, y, 336, 514);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }
}
