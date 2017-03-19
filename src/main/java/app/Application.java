package app;

import app.model.EngineExecutor;
import app.model.EngineFactory;
import app.presenter.GameWindow;

public class Application {
    public static void main(String... args) {
        GameWindow window = new GameWindow();
        window.show();

        EngineFactory factory = new EngineFactory();
        factory.addRenderListener(window::render);
        factory.addUpdateListener(window);

        EngineExecutor executor = factory.createExecutor();
        executor.start();
    }
}
