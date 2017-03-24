package app.view.render;

import java.util.List;

public interface Operator {
    Tableable getTableable();

    boolean put(List<Operator> operators);

    boolean canGatherOneElement();

    boolean canGatherTwoElements(Operator second);

    boolean canGatherThreeElements(Operator second, Operator third);
}
