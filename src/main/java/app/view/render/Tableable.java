package app.view.render;

public interface Tableable {
    Operator getOperator();

    int getXX();

    int getYY();

    void moveTo(int x, int y, double duration);
}
