package app.presenter.table;

import app.model.turn.factory.TurnFactory;
import app.view.render.Tableable;

import java.util.List;

public interface Combination {
    boolean applies(List<Tableable> tableables);

    boolean fulfills(List<Tableable> tableables);

    TurnFactory getTurnFactory();
}
