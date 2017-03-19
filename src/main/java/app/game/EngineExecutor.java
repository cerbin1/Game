package app.game;

import java.util.List;

import static java.lang.Thread.sleep;

public class EngineExecutor {
    private final List<Updatable> onUpdate;
    private final List<Runnable> onRender;
    private volatile boolean shouldStop = false;

    EngineExecutor(List<Updatable> onUpdate, List<Runnable> onRender) {
        this.onUpdate = onUpdate;
        this.onRender = onRender;
    }

    public void start() {
        if (shouldStop) {
            throw new RuntimeException("EngineExecutor restarted");
        }

        startExecuting();
    }

    private void startExecuting() {
        double previous = System.nanoTime();
        while (!shouldStop) {
            double current = System.nanoTime();
            double elapsed = current - previous;
            previous = current;
            updateAll(elapsed / 1.0e9);
            renderAll();
            try {
                sleep(2);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void updateAll(double elapsedSeconds) {
        onUpdate.forEach(updatable -> updatable.update(elapsedSeconds));
    }

    private void renderAll() {
        onRender.forEach(Runnable::run);
    }
}
