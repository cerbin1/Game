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
        Tokens playerTokens = player.getTokensIncludingBoughtCards();
        Tokens cost = card.getCost();
        Tokens gameTokens = game.getTokens();
        if (playerHaveEnoughTokensToBuyCard(playerTokens, cost)) {
            if (game.getAvailableCards().contains(card)) {
                game.removeCard(card);
                player.addCard(card);
            } else {
                throw new IllegalTurnException();
            }
            if (player.getCards().contains(card)) {
                card.setReserved(false);
            } else {
                throw new IllegalTurnException();
            }
            player.setTokens(getPlayerTokensAfterBuyingCard(playerTokens, cost));
            game.setTokens(getGameTokensAfterUpdate(cost, gameTokens));
        } else {
            throw new IllegalTurnException();
        }
    }

    private boolean playerHaveEnoughTokensToBuyCard(Tokens playerTokens, Tokens cost) {
        return playerTokens.getGreen() >= cost.getGreen() && playerTokens.getPurple() >= cost.getPurple() && playerTokens.getBlue() >= cost.getBlue() && playerTokens.getBlack() >= cost.getBlack() && playerTokens.getRed() >= cost.getRed();
    }

    private Tokens getGameTokensAfterUpdate(Tokens cost, Tokens gameTokens) {
        return new Tokens(gameTokens.getGreen() + cost.getGreen(), gameTokens.getPurple() + cost.getPurple(), gameTokens.getBlue() + cost.getBlue(), gameTokens.getBlack() + cost.getBlack(), gameTokens.getRed() + cost.getRed());
    }

    private Tokens getPlayerTokensAfterBuyingCard(Tokens playerTokens, Tokens cost) {
        return new Tokens(playerTokens.getGreen() - cost.getGreen(), playerTokens.getPurple() - cost.getPurple(), playerTokens.getBlue() - cost.getBlue(), playerTokens.getBlack() - cost.getBlack(), playerTokens.getRed() - cost.getRed());
    }
}
