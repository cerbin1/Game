package app.model.turn;

import app.model.Game;
import app.model.Player;
import app.model.token.TokensAmount;

public class AcquireTokensTurn extends Turn {
    private TokensAmount tokensAmount;

    public AcquireTokensTurn(TokensAmount tokensAmount) {
        this.tokensAmount = tokensAmount;
    }

    @Override
    public void invoke(Game game) {
        Player player = game.getCurrentPlayer();
        if (!threeDifferentTokensChose(game.getTokensAmount()) && !twoSameTokensChose(game.getTokensAmount())) {
            throw new IllegalTurnException("Invalid amount of tokensAmount");
        }

        player.setTokensAmount(player.getTokensAmount().add(tokensAmount));
        game.setTokensAmount(game.getTokensAmount().subtract(tokensAmount));

    }

    private boolean twoSameTokensChose(TokensAmount gameTokens) {
        return tokensAmount.asMap().entrySet().stream().filter(tokenColor -> tokenColor.getValue() == 2 &&
                gameTokens.asMap().get(tokenColor.getKey()) > tokenColor.getValue()).count() == 1 &&
                tokensAmount.asMap().entrySet().stream().filter(tokenColor -> tokenColor.getValue() == 0).count() == 4;
    }

    private boolean threeDifferentTokensChose(TokensAmount gameTokens) {
        return (tokensAmount.asMap().entrySet().stream().filter(tokenColor -> tokenColor.getValue() == 1).count() == 3) &&
                tokensAmount.asMap().entrySet().stream().filter(tokenColor -> tokenColor.getValue() == 0).count() == 2 &&
                tokensAmount.asMap().entrySet().stream().noneMatch(tokenColorIntegerEntry -> tokenColorIntegerEntry.getValue() <= gameTokens.asMap().get(tokenColorIntegerEntry.getKey()))
                ;
    }
}
