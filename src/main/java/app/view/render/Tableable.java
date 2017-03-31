package app.view.render;

import java.awt.*;

public interface Tableable {
    Operator getOperator();

    void moveTo(Point point, double duration);

    void moveTo(int x, int y, double duration);

    Point getStartingPoint();

    Point getDestination();

    void moveToTable(int x, int y, int size);
}