package app.presenter.table;

import app.model.turn.factory.TurnFactory;
import app.model.turn.factory.AcquireThreeTokensTurnFactory;
import app.view.render.Tableable;

import java.util.List;

import static app.presenter.table.TokensCombination.isTokenNotVersatile;
import static app.presenter.table.TokensCombination.validateTokenColorsCount;

public class ThreeTokensCombination implements Combination {

    @Override
    public boolean applies(List<Tableable> tableables) {
        if (tableables.isEmpty()) {
            return true;
        }

        if (tableables.size() == 1) {
            return isTokenNotVersatile(tableables.get(0));
        }

        if (tableables.size() == 2) {
            return validateTokenColorsCount(tableables, 2, 2);
        }

        if (tableables.size() == 3) {
            return validateTokenColorsCount(tableables, 3, 3);
        }

        return false;
    }

    @Override
    public boolean fulfills(List<Tableable> tableables) {
        if (tableables.size() == 3) {
            return validateTokenColorsCount(tableables, 3, 3);
        }

        return false;
    }

    @Override
    public TurnFactory getTurnFactory() {
        return new AcquireThreeTokensTurnFactory();
    }
}
