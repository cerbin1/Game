package app.presenter.table;

import app.view.render.Tableable;
import app.view.render.vo.CardVO;
import app.view.render.vo.TokenVO;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ReserveCombination implements Combination {
    @Override
    public boolean applies(List<Tableable> tableables) {
        if (tableables.isEmpty()) {
            return true;
        }

        if (tableables.size() == 1) {
            return validItem(tableables.get(0));
        }

        if (tableables.size() == 2) {
            return fulfills(tableables);
        }

        return false;
    }

    @Override
    public boolean fulfills(List<Tableable> tableables) {
        if (tableables.size() != 2) {
            return false;
        }

        List<Tableable> cardsOrVersatile = tableables.stream()
                .filter(this::validItem)
                .collect(toList());

        if (cardsOrVersatile.size() == 2) {
            return cardsOrVersatile.get(0).getClass() != cardsOrVersatile.get(1).getClass();
        }

        return false;
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
