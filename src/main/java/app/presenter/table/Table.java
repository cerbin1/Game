package app.presenter.table;

import app.presenter.UnexpectedGatherException;
import app.presenter.UnexpectedTakeException;
import app.view.render.Tableable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Table {
    private final List<Combination> combinations = asList(
            new CardCombination(),
            new ReserveCombination(),
            new TwoTokensCombination(),
            new ThreeTokensCombination()
    );

    private final List<Tableable> tableables = new ArrayList<>(3);

    public boolean put(Tableable tableable) {
        if (canPut(tableable)) {
            tableables.add(tableable);
            return true;
        }
        return false;
    }

    private boolean canPut(Tableable tableable) {
        List<Tableable> shallowCopy = new ArrayList<>(tableables);
        shallowCopy.add(tableable);

        return combinations
                .stream()
                .anyMatch(combination -> combination.applies(shallowCopy));
    }

    public void take(Tableable tableable) {
        if (tableables.contains(tableable)) {
            tableables.remove(tableable);
        } else {
            throw new UnexpectedTakeException();
        }
    }

    public boolean canGather() {
        return combinations
                .stream()
                .anyMatch(combination -> combination.fulfills(tableables));
    }

    public List<Tableable> gather() {
        if (canGather()) {
            List<Tableable> copy = new ArrayList<>(tableables);
            tableables.clear();
            return copy;
        }
        throw new UnexpectedGatherException();
    }
}
