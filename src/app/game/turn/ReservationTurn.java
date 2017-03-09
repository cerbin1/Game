package app.game.turn;

import app.game.Game;
import app.game.Player;
import app.game.card.Card;
import app.game.token.Tokens;

public class ReservationTurn extends Turn {
    private Card card;

    public ReservationTurn(Card card) {
        this.card = card;
    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
        Tokens playerTokens = player.getTokens();
        Tokens tokensAfterReservation = new Tokens(playerTokens.getGreen(), playerTokens.getPurple(), playerTokens.getBlue(), playerTokens.getBlack(), playerTokens.getRed(), playerTokens.getVersatile() + 1);
        player.setTokens(tokensAfterReservation);
        game.removeCard(card);
        card.setReserved(true);
        player.addCard(card);
    }
}
