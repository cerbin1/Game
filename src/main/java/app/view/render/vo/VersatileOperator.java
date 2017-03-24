package app.view.render.vo;

import app.view.render.Operator;
import app.view.render.Tableable;

import java.util.List;

public class VersatileOperator implements Operator {

    private final Tableable tableable;

    public VersatileOperator(Tableable tableable) {
        this.tableable = tableable;
    }

    @Override
    public Tableable getTableable() {
        return tableable;
    }

    @Override
    public boolean put(List<Operator> operators) {
        if (operators.size() == 1) {
            if (operators.get(0) instanceof CardOperator) {
                operators.add(this);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canGatherOneElement() {
        return false;
    }

    @Override
    public boolean canGatherTwoElements(Operator second) {
        return second instanceof CardOperator;
    }

    @Override
    public boolean canGatherThreeElements(Operator second, Operator third) {
        return false;
    }
}
