package app.model.turn.factory;

import app.model.turn.BuyCardTurn;
import app.model.turn.Factory;
import app.model.turn.Turn;
import app.view.render.Tableable;
import app.view.render.vo.CardVO;

import java.util.List;

public class BuyCardTurnFactory implements Factory {
    @Override
    public Turn getTurn(List<Tableable> tableables) {
        CardVO cardVO = (CardVO) tableables.get(0);
        return new BuyCardTurn(cardVO.getCard());
    }
}
