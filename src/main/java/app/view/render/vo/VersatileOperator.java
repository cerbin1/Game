package app.view.render.vo;

import app.view.render.Operator;
import app.view.render.Tableable;

public class VersatileOperator implements Operator {

    private final Tableable tableable;

    public VersatileOperator(Tableable tableable) {
        this.tableable = tableable;
    }

    @Override
    public Tableable getTableable() {
        return tableable;
    }
}
