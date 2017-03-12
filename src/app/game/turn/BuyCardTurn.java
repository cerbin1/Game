package app.game.turn;

import app.game.Game;
import app.game.Player;
import app.game.card.Card;
import app.game.token.BuyingResult;
import app.game.token.Resources;
import app.game.token.Tokens;

public class BuyCardTurn extends Turn {
    private final Card card;

    public BuyCardTurn(Card card) {
        this.card = card;
    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
        Resources resources = player.getResourcesNEW();
        Tokens cardCost = card.getCost();
        BuyingResult result = resources.buy(cardCost);
        if (!result.canBuy() || !game.hasCard(card) && !player.hasCard(card)) {
            throw new IllegalTurnException();
        } else {
            Tokens tokensSpent = player.getTokens().subtract(result.getRemaining());
            game.setTokens(game.getTokens().add(tokensSpent));
            player.setTokens(result.getRemaining());
            card.setReserved(false);
            game.removeCard(card);
            player.addCard(card);
        }
    }
}
