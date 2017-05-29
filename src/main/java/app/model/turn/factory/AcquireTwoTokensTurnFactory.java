package app.model.turn.factory;

import app.model.token.TokenColor;
import app.model.token.TokensAmount;
import app.model.turn.AcquireTokensTurn;
import app.model.turn.Turn;
import app.model.turn.UnexpectedCreateTurnException;
import app.view.render.Tableable;
import app.view.render.vo.TokenVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AcquireTwoTokensTurnFactory implements TurnFactory {
    @Override
    public Turn getTurn(List<Tableable> tableables) {
        if (tableables.size() == 2) {
            if (((TokenVO) tableables.get(0)).getColor() == ((TokenVO) tableables.get(1)).getColor()) {
                Map<TokenColor, Integer> tokensToAcquire = new HashMap<>();
                tokensToAcquire.put(((TokenVO) tableables.get(0)).getColor(), 2);
                return new AcquireTokensTurn(new TokensAmount(tokensToAcquire, 0));
            }
        }
        throw new UnexpectedCreateTurnException();
    }
}
