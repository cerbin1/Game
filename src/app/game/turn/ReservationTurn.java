package app.game.turn;

import app.game.Game;
import app.game.Player;
import app.game.card.Card;

public class ReservationTurn extends Turn {
    private Card card;

    public ReservationTurn(Card card) {
        this.card = card;
    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
        player.incVersatile(1);
        game.decVersatile(1);
        game.removeCard(card);
        card.setReserved(true);
        player.addCard(card);
    }
}
