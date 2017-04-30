package app.model.turn.factory;

import app.model.turn.AcquireTokensTurn;
import app.model.turn.Factory;
import app.model.turn.Turn;
import app.model.turn.UnexpectedCreateTurnException;
import app.view.render.Tableable;
import app.view.render.vo.TokenVO;

import java.util.List;

public class AcquireThreeTokensTurnFactory implements Factory {
    @Override
    public Turn getTurn(List<Tableable> tableables) {
        if (tableables.size() == 3) {
            long uniqueTokens = tableables.stream()
                    .map(tableable -> (TokenVO) tableable)
                    .map(TokenVO::getColor)
                    .distinct()
                    .count();

            if (uniqueTokens == 3) {
                return new AcquireTokensTurn(null);
            }
        }
        throw new UnexpectedCreateTurnException();
    }
}
