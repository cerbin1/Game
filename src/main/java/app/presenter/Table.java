package app.presenter;

import app.view.render.Operator;
import app.view.render.Tableable;

import java.util.ArrayList;
import java.util.List;

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
        Operator first = operators.get(0);
        if (operators.size() == 1) {
            return first.canGatherOneElement();
        }

        Operator second = operators.get(1);
        if (operators.size() == 2) {
            return first.canGatherTwoElements(second);
        }

        if (operators.size() == 3) {
            Operator third = operators.get(2);
            return first.canGatherThreeElements(second, third);
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
