package app.presenter;

import app.view.render.Tableable;

import java.util.List;

class Table {
    boolean put(Tableable tableable) {
        return true;
    }

    boolean take(Tableable tableable) {
        return true;
    }

    boolean canGather() {
        return true;
    }

    List<Tableable> gather() {
        return null;
    }
}
