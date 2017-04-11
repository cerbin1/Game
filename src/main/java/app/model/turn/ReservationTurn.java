package app.model.turn;

import app.model.Game;
import app.model.Player;
import app.model.card.Card;

public class ReservationTurn extends Turn {
    private final Card card;

    ReservationTurn(Card card) {
        this.card = card;
    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
        if (player.hasCard(card)) {
            throw new IllegalTurnException("Card is already possessed by player");
        }
        if (!game.hasCard(card)) {
            throw new IllegalTurnException("Card is not available in model");
        }
        if (card.isReserved()) {
            throw new IllegalTurnException("Card already reserved");
        }
        if (game.getVersatile() >= 1) {
            player.incVersatile();
            game.decVersatile();
        }
        card.setReserved(true);
        game.removeCard(card);
        player.addCard(card);
    }
}
