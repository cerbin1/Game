package app.presenter;

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

    public boolean take(Tableable tableable) {
        if (table.take(tableable)) {
            Point previousPoint = previousPosition.get(tableable);
            tableable.moveTo(previousPoint.x, previousPoint.y, 0.5);
            previousPosition.remove(tableable);
            return true;
        }
        return false;
    }

    public boolean canGather() {
        return table.canGather();
    }

    public boolean has(Tableable tableable) {
        return previousPosition.containsKey(tableable);
    }

    public boolean isEmpty() {
        return previousPosition.isEmpty();
    }

    public void gather() {
        if (canGather()) {
            for (Map.Entry<Tableable, Point> entry : previousPosition.entrySet()) {
                entry.getKey().moveTo(2000, 1000, 0.5);
            }
            table.gather();
            previousPosition.clear();
        }
    }
}
