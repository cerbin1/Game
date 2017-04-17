package app.presenter.table;

import app.view.render.Tableable;
import app.view.render.vo.CardVO;

import java.util.List;

public class CardCombination implements Combination {

    @Override
    public boolean applies(List<Tableable> tableables) {
        if (tableables.size() > 1) {
            return false;
        }
        return tableables.get(0) instanceof CardVO;
    }

    @Override
    public boolean fulfills(List<Tableable> tableables) {
        if (tableables.size() != 1) {
            return false;
        }
        return tableables.get(0) instanceof CardVO;
    }
}
