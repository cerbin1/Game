package app;

import app.config.Configuration;
import app.config.ConfigurationLoader;
import app.config.FileConfigurationFile;
import app.model.EngineExecutor;
import app.model.EngineFactory;
import app.presenter.GameWindow;

import java.io.File;

public class Application {
    public static void main(String... args) {
        Configuration.use(new ConfigurationLoader(new FileConfigurationFile(new File("config.properties")), args));

        GameWindow window = new GameWindow();
        window.show();

        EngineFactory factory = new EngineFactory();
        factory.addRenderListener(window::render);
        factory.addUpdateListener(window);

        EngineExecutor executor = factory.createExecutor();
        executor.start();
    }
}
