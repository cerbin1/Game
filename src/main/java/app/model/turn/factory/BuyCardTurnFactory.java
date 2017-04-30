package app.model.turn.factory;

import app.model.turn.BuyCardTurn;
import app.model.turn.Turn;
import app.model.turn.UnexpectedCreateTurnException;
import app.view.render.Tableable;
import app.view.render.vo.CardVO;

import java.util.List;

public class BuyCardTurnFactory implements TurnFactory {
    @Override
    public Turn getTurn(List<Tableable> tableables) {
        if (tableables.size() == 1) {
            CardVO cardVO = (CardVO) tableables.get(0);
            return new BuyCardTurn(cardVO.getCard());
        }
        throw new UnexpectedCreateTurnException();
    }
}
