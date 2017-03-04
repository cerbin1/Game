package app;

import app.game.EngineExecutor;
import app.game.EngineFactory;
import app.view.GameWindow;

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
