package app.model.turn;

import app.model.Game;
import app.model.Player;
import app.model.token.TokensAmount;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class AcquireTokensTurn extends Turn {
    private TokensAmount tokensAmount;

    public AcquireTokensTurn(TokensAmount tokensAmount) {
        this.tokensAmount = tokensAmount;
    }

    @Override
    public void invoke(Game game) {
        if (!isEnoughTokensInGame(game.getTokensAmount())) {
            throw new IllegalTurnException("Not enough tokens in game");
        }
        if (!canAcquireThreeDifferentTokens() && !canAcquireTwoSameTokens(game.getTokensAmount())) {
            throw new IllegalTurnException("Invalid amount of tokensAmount");
        }
        Player player = game.getCurrentPlayer();
        player.setTokensAmount(player.getTokensAmount().add(tokensAmount));
        game.setTokensAmount(game.getTokensAmount().subtract(tokensAmount));
    }

    private boolean isEnoughTokensInGame(TokensAmount gameTokens) {
        return tokensAmount.asMap().entrySet().stream().anyMatch(token -> gameTokens.get(token.getKey()) - token.getValue() < 0);
    }

    private boolean canAcquireTwoSameTokens(TokensAmount gameTokens) {
        return tokensAmount.asMap().values().stream().sorted().collect(toList()).equals(asList(0, 0, 0, 0, 2)) &&
                tokensAmount.asMap().entrySet().stream().noneMatch(token -> token.getValue() == 2 && gameTokens.get(token.getKey()) - token.getValue() == 1);
    }

    private boolean canAcquireThreeDifferentTokens() {
        return tokensAmount.asMap().values().stream().sorted().collect(toList()).equals(asList(0, 0, 1, 1, 1));
    }
}
