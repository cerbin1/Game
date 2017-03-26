package app.view.render;

public interface Tableable {
    Operator getOperator();

    int getX();

    int getY();

    void moveTo(int x, int y, double duration);
}
