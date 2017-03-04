package app.game;

import java.util.ArrayList;
import java.util.List;

public class EngineFactory {
    private final List<Updatable> onUpdate = new ArrayList<>();
    private final List<Runnable> onRender = new ArrayList<>();

    public void addUpdateListener(Updatable updateListener) {
        onUpdate.add(updateListener);
    }

    public void addRenderListener(Runnable renderListener) {
        this.onRender.add(renderListener);
    }

    public EngineExecutor createExecutor() {
        return new EngineExecutor(onUpdate, onRender);
    }
}
