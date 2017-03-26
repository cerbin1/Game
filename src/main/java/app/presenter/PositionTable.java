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
        if (table.put(tableable)) {
            Point position = tableable.getDestination();
            this.previousPosition.put(tableable, position);
            return true;
        }
        return false;
    }

    public void take(Tableable tableable) {
        if (table.take(tableable)) {
            previousPosition.remove(tableable);
        }
    }

    public void gather() {

    }

    public boolean has(Tableable tableable) {
        return previousPosition.containsKey(tableable);
    }

    public Point getPoint(Tableable tableable) {
        return previousPosition.get(tableable);
    }

    public Map<Tableable, Point> getPositionsTable() {
        return previousPosition;
    }
}
