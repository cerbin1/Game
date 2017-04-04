package app.view.render;

import java.awt.*;

public interface Tableable {
    Operator getOperator();

    void moveTo(int x, int y, double duration);

    Point getDestination();

    void moveToTable(int x, int y, int size);
}
