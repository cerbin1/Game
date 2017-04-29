package app.presenter.table;

import app.model.turn.Factory;
import app.model.turn.TurnFactory;
import app.view.render.Tableable;
import app.view.render.vo.CardVO;

import java.util.List;

public class CardCombination implements Combination {

    @Override
    public boolean applies(List<Tableable> tableables) {
        if (tableables.isEmpty()) {
            return true;
        }

        return fulfills(tableables);
    }

    @Override
    public boolean fulfills(List<Tableable> tableables) {
        if (tableables.size() == 1) {
            return tableables.get(0) instanceof CardVO;
        }

        return false;
    }

    @Override
    public Factory getTurnFactory() {
        return new TurnFactory.BuyCardTurnFactory();
    }
}
