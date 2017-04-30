package app.model.turn.factory;

import app.model.turn.ReservationTurn;
import app.model.turn.Turn;
import app.view.render.Tableable;
import app.view.render.vo.CardVO;

import java.util.List;

public class ReservationTurn1Factory implements TurnFactory {
    @Override
    public Turn getTurn(List<Tableable> tableables) {
        CardVO cardVO = (CardVO) tableables.get(0); // TODO implement
        return new ReservationTurn(cardVO.getCard());
    }
}
