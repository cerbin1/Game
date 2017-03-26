package app.view.render;

public interface Tableable {
    Operator getOperator();

    void moveTo(int x, int y, double duration);
}
