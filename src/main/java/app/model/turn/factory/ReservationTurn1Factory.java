package app.model.turn.factory;

import app.model.turn.ReservationTurn;
import app.model.turn.Turn;
import app.model.turn.UnexpectedCreateTurnException;
import app.view.render.Tableable;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;

import java.util.List;

public class ReservationTurn1Factory implements TurnFactory {
    @Override
    public Turn getTurn(List<Tableable> tableables) {
        if (tableables.size() == 2) {
            Tableable first = tableables.get(0);
            Tableable second = tableables.get(1);
            if (isCardAndVersatile(first, second)) {
                return new ReservationTurn(((CardVO) first).getCard());
            }
            if (isVersatileAndCard(first, second)) {
                return new ReservationTurn(((CardVO) second).getCard());
            }
        }
        throw new UnexpectedCreateTurnException();
    }

    private boolean isVersatileAndCard(Tableable first, Tableable second) {
        return second instanceof CardVO && first instanceof TokenVO && ((TokenVO) first).isVersatile();
    }

    private boolean isCardAndVersatile(Tableable first, Tableable second) {
        return first instanceof CardVO && second instanceof TokenVO && ((TokenVO) second).isVersatile();
    }
}
