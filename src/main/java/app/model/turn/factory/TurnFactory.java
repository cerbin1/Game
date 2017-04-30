package app.model.turn.factory;

import app.model.turn.Turn;
import app.view.render.Tableable;

import java.util.List;

public interface TurnFactory {
    Turn getTurn(List<Tableable> tableables);
}
