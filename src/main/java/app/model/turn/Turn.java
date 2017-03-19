package app.model.turn;

import app.model.Game;

public abstract class Turn {
    public abstract void invoke(Game game);
}
