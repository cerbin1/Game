package app.presenter.table;

import app.view.render.Tableable;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;

import java.util.List;

public class ReserveCombination implements Combination {
    @Override
    public boolean applies(List<Tableable> tableables) {
        if (tableables.size() > 2) {
            return false;
        }
        return tableables.stream().allMatch(this::validItem);
    }

    @Override
    public boolean fulfills(List<Tableable> tableables) {
        if (tableables.size() != 2) {
            return false;
        }
        return tableables.stream().allMatch(this::validItem);
    }

    private boolean validItem(Tableable tableable) {
        if (tableable instanceof CardVO) {
            return true;
        }
        if (tableable instanceof TokenVO) {
            return ((TokenVO) tableable).isVersatile();
        }
        return false;
    }
}
