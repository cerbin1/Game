package app.view.render.vo;

import app.view.render.Operator;
import app.view.render.Tableable;

public class CardOperator implements Operator {
    private Tableable tableable;

    public CardOperator(Tableable tableable) {
        this.tableable = tableable;
    }

    @Override
    public Tableable getTableable() {
        return tableable;
    }
}
