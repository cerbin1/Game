package app.presenter;

import app.model.token.TokenColor;
import app.view.render.Operator;
import app.view.render.Tableable;
import app.view.render.vo.CardOperator;
import app.view.render.vo.TokenOperator;
import app.view.render.vo.VersatileOperator;

import java.util.ArrayList;
import java.util.List;

import static app.model.token.TokenColor.values;
import static java.util.stream.Collectors.toList;

class Table {
    private final List<Operator> operators = new ArrayList<>();

    boolean put(Tableable tableable) {
        Operator operator = tableable.getOperator();
        if (operators.isEmpty()) {
            operators.add(operator);
            return true;
        }
        return operator.put(operators);
    }

    void take(Tableable tableable) {
        if (!operators.contains(tableable.getOperator())) {
            throw new UnexpectedTakeException();
        }
        operators.remove(tableable.getOperator());
    }

    boolean canGather() {
        if (operators.isEmpty()) {
            return false;
        }
        if (operators.size() == 1) {
            if (operators.stream().anyMatch(o -> o instanceof CardOperator)) {
                return true;
            }
            if (operators.stream().anyMatch(o -> o instanceof TokenOperator)) {
                return false;
            }
            if (operators.stream().anyMatch(o -> o instanceof VersatileOperator)) {
                return false;
            }
        }
        if (operators.size() == 2) {
            if (operators.stream().filter(o -> o instanceof CardOperator).count() + operators.stream().filter(o -> o instanceof VersatileOperator).count() == 2) {
                return true;
            }
            for (TokenColor color : values()) {
                if (operators.stream().filter(o -> o instanceof TokenOperator && ((TokenOperator) o).getColor() == color).count() == 2) {
                    return true;
                }
            }
            return false;
        }
        if (operators.size() == 3) {
            for (TokenColor color : values()) {
                if (operators.stream().filter(o -> o instanceof TokenOperator && ((TokenOperator) o).getColor() == color).count() > 1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    List<Tableable> gather() {
        if (!canGather()) {
            throw new UnexpectedGatherException();
        }
        List<Tableable> copy = operators.stream().map(Operator::getTableable).collect(toList());
        operators.clear();
        return copy;
    }
}
