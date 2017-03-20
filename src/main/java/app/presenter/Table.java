package app.presenter;

import app.view.render.ViewObject;

import java.util.HashSet;
import java.util.Set;

public class Table {
    private final Set<ViewObject> viewObjects = new HashSet<>();

    boolean put(ViewObject vo) {
        return false;
    }

    public void take(ViewObject vo) {
        if (viewObjects.contains(vo)) {
            viewObjects.remove(vo);
        } else {
            throw new IllegalViewObjectTaking("Unexpected gather");
        }
    }

    boolean canGather() {
        return true;
    }

    Set<ViewObject> gather() {
        Set<ViewObject> copy = new HashSet<>();
        copy.addAll(viewObjects);
        viewObjects.clear();
        return copy;
    }
}
