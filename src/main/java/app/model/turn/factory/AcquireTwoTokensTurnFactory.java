package app.model.turn.factory;

import app.model.turn.AcquireTokensTurn;
import app.model.turn.Turn;
import app.model.turn.UnexpectedCreateTurnException;
import app.view.render.Tableable;
import app.view.render.vo.TokenVO;

import java.util.List;

public class AcquireTwoTokensTurnFactory implements TurnFactory {
    @Override
    public Turn getTurn(List<Tableable> tableables) {
        if (tableables.size() == 2) {
            if (((TokenVO) tableables.get(0)).getColor() == ((TokenVO) tableables.get(1)).getColor()) {
                return new AcquireTokensTurn(null);
            }
        }
        throw new UnexpectedCreateTurnException();
    }
}
