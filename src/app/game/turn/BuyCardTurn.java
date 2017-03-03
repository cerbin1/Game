package app.game.turn;

import app.game.Game;
import app.game.Player;
import app.game.cards.Card;

public class BuyCardTurn extends Turn {
    public BuyCardTurn(Card card) {

    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
    }
}
