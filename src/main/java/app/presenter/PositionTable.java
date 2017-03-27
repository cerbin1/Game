package app.presenter;

import app.view.render.Tableable;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PositionTable {
    private final int x, y;
    private Table table = new Table();

    private final Map<Tableable, Point> previousPosition = new HashMap<>();

    public PositionTable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean put(Tableable tableable) {
        return table.put(tableable);
    }

    public boolean take(Tableable tableable) {
        return table.take(tableable);
    }

    public void gather() {

    }

    public boolean has(Tableable tableable) {
        return previousPosition.containsKey(tableable);
    }

    public Point getPreviousPoint(Tableable tableable) {
        return previousPosition.get(tableable);
    }

    public Map<Tableable, Point> getPositionsTable() {
        return previousPosition;
    }

    public void set(Tableable tableable) {
        if (has(tableable)) {
            if (take(tableable)) {
                tableable.moveTo(getPreviousPoint(tableable), 0.5);
                previousPosition.remove(tableable);
            }
        } else if (put(tableable)) {
            Point currentPosition = tableable.getDestination();
            previousPosition.put(tableable, currentPosition);
            tableable.moveToTable(0);
        }
    }
}
