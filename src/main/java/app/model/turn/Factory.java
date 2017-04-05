package app.model.turn;

import app.view.render.Tableable;

import java.util.List;

public interface Factory {
    Turn getTurn(List<Tableable> tableables);
}
