package app.game.turn;

import app.game.Game;
import app.game.Player;
import app.game.card.Card;
import app.game.token.Tokens;

public class BuyCardTurn extends Turn {
    private Card card;

    public BuyCardTurn(Card card) {
        this.card = card;
    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
        Tokens playerTokens = player.getTokens();
        Tokens cost = card.getCost();
        Tokens gameTokens = game.getTokens();
        player.addCard(card);
        player.setTokens(getPlayerTokensAfterBuyingCard(playerTokens, cost));
        game.setTokens(new Tokens(gameTokens.getGreen() + cost.getGreen(), gameTokens.getPurple() + cost.getPurple(), gameTokens.getBlue() + cost.getBlue(), gameTokens.getBlack() + cost.getBlack(), gameTokens.getRed() + cost.getRed()));
    }

    private Tokens getPlayerTokensAfterBuyingCard(Tokens playerTokens, Tokens cost) {
        return new Tokens(playerTokens.getGreen() - cost.getGreen(), playerTokens.getPurple() - cost.getPurple(), playerTokens.getBlue() - cost.getBlue(), playerTokens.getBlack() - cost.getBlack(), playerTokens.getRed() - cost.getRed());
    }
}
