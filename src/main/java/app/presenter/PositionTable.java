package app.presenter;

import app.model.turn.Turn;
import app.presenter.table.Table;
import app.view.render.Tableable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PositionTable {
    private final int x, y;
    private final Table table = new Table();

    private final Map<Tableable, Point> previousPosition = new HashMap<>();

    public PositionTable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean put(Tableable tableable) {
        if (table.put(tableable)) {
            previousPosition.put(tableable, tableable.getDestination());
            tableable.moveToTable(x, y, previousPosition.size());
            return true;
        }
        return false;
    }

    void take(Tableable tableable) {
        table.take(tableable);
        Point previousPoint = previousPosition.get(tableable);
        tableable.moveTo(previousPoint.x, previousPoint.y, 0.5);
        previousPosition.remove(tableable);
    }

    boolean canGather() {
        return table.canGather();
    }

    boolean has(Tableable tableable) {
        return previousPosition.containsKey(tableable);
    }

    boolean isEmpty() {
        return previousPosition.isEmpty();
    }

    Turn gather() {
        for (Map.Entry<Tableable, Point> entry : previousPosition.entrySet()) {
            entry.getKey().moveTo(2000, 1000, 0.5);
        }
        previousPosition.clear();
        return table.gather();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
