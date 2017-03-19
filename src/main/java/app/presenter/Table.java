package app.presenter;

import app.view.render.ViewObject;

import java.util.ArrayList;
import java.util.List;

public class Table {
    boolean put(ViewObject vo) {
        return false;
    }

    public void take(ViewObject vo) {

    }

    boolean canGather() {
        return true;
    }

    List<ViewObject> gather() {
        return new ArrayList<>();
    }
}
