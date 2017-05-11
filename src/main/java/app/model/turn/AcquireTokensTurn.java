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
        if (!three() && !two()) {
            throw new IllegalTurnException("Invalid amount of tokensAmount");
        }
        player.setTokensAmount(player.getTokensAmount().add(tokensAmount));
        game.setTokensAmount(game.getTokensAmount().subtract(tokensAmount));
    }

    private boolean two() {
        return tokensAmount.asMap().entrySet().stream().filter(tokenColorIntegerEntry -> tokenColorIntegerEntry.getValue() == 2).count() == 1 && tokensAmount.asMap().entrySet().stream().filter(tokenColorIntegerEntry -> tokenColorIntegerEntry.getValue() == 0).count() == 4;
    }

    private boolean three() {
        return (tokensAmount.asMap().entrySet().stream().filter(tokenColorIntegerEntry -> tokenColorIntegerEntry.getValue() == 1).count() == 3) &&
                tokensAmount.asMap().entrySet().stream().filter(tokenColorIntegerEntry -> tokenColorIntegerEntry.getValue() == 0).count() == 2;
    }

    private boolean areThreeSameTokensChose() {
        return tokensAmount.asMap().entrySet().stream().anyMatch(tokenColorIntegerEntry -> tokenColorIntegerEntry.getValue() == 3);
    }
}
