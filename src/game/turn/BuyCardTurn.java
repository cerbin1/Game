package game.turn;

import game.Game;
import game.Player;
import game.cards.Card;

public class BuyCardTurn extends Turn {
    public BuyCardTurn(Card card) {

    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
    }
}
