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
        Tokens playerTokens = player.getTokensIncludingBoughtCards();
        Tokens cost = card.getCost();
        if (player.getCards().contains(card) || game.getAvailableCards().contains(card) && player.isAbleToBuyCard(cost) || isPlayerAbleToBuyCardWithVersatileTokens(playerTokens, cost)) {
            Tokens costWithVersatileTokens = new Tokens(cost.getGreen(), cost.getPurple(), cost.getBlue(), cost.getBlack(), cost.getRed(), versatileNeeded);
            game.removeCard(card);
            player.setTokens(getPlayerTokensAfterBuyingCard(playerTokens, cost));
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

    private Tokens getGameTokensAfterUpdate(Tokens cost, Tokens gameTokens) {
        return new Tokens(gameTokens.getGreen() + cost.getGreen(), gameTokens.getPurple() + cost.getPurple(), gameTokens.getBlue() + cost.getBlue(), gameTokens.getBlack() + cost.getBlack(), gameTokens.getRed() + cost.getRed(), gameTokens.getVersatile() - versatileNeeded);
    }

    private Tokens getPlayerTokensAfterBuyingCard(Tokens playerTokens, Tokens cost) {
        return playerTokens.subtract(new Tokens(cost.getGreen() + green, cost.getPurple() + purple, cost.getBlue() + blue, cost.getBlack() + black, cost.getRed() + red, versatileNeeded));
    }
}
