package app.game.turn;

import app.game.Game;
import app.game.Player;
import app.game.card.Card;
import app.game.token.Tokens;

public class BuyCardTurn extends Turn {
    private final Card card;
    private int versatileNeeded = 0;
    private int green, purple, blue, black, red;

    public BuyCardTurn(Card card) {
        this.card = card;
    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
        Tokens playerResources = player.getTokensIncludingBoughtCards();
        Tokens cost = card.getCost();
        if (player.getCards().contains(card) || game.getAvailableCards().contains(card) && player.isAbleToBuyCard(cost) || isPlayerAbleToBuyCardWithVersatileTokens(playerResources, cost)) {
            Tokens costWithVersatileTokens = new Tokens(cost.getGreen(), cost.getPurple(), cost.getBlue(), cost.getBlack(), cost.getRed(), versatileNeeded);
            game.removeCard(card);
            player.setTokens(getTokensAfterBuyingCard(playerResources, cost));
            game.setTokens(costWithVersatileTokens);
            if (!card.isReserved()) {
                player.addCard(card);
            }
            card.setReserved(false);
        } else {
            throw new IllegalTurnException();
        }
    }

    private boolean isPlayerAbleToBuyCardWithVersatileTokens(Tokens playerResources, Tokens cost) {
        int result = 0;
        green = playerResources.getGreen() - cost.getGreen();
        purple = playerResources.getPurple() - cost.getPurple();
        blue = playerResources.getBlue() - cost.getBlue();
        black = playerResources.getBlack() - cost.getBlack();
        red = playerResources.getRed() - cost.getRed();
        if (green < 0) {
            result += green;
        }
        if (purple < 0) {
            result += purple;
        }
        if (blue < 0) {
            result += blue;
        }
        if (black < 0) {
            result += black;
        }
        if (red < 0) {
            result += red;
        }
        if (result < 0 && playerResources.getVersatile() >= -result) {
            versatileNeeded = result;
            return true;
        } else {
            return false;
        }
    }

    private Tokens getTokensAfterBuyingCard(Tokens playerTokens, Tokens cost) {
        return new Tokens(playerTokens.getGreen() - (cost.getGreen() + green), playerTokens.getPurple() - (cost.getPurple() + purple), playerTokens.getBlue() - (cost.getBlue() + blue), playerTokens.getBlack() - (cost.getBlack() + black), playerTokens.getRed() - (cost.getRed() + red), playerTokens.getVersatile() + versatileNeeded);
    }
}
