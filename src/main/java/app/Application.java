package app;

import app.model.config.Configuration;
import app.model.config.ConfigurationLoader;
import app.presenter.LwjglWindow;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.newdawn.slick.Input.disableControllers;

public class Application {
    public static void main(String... args) {
        Configuration.use(new ConfigurationLoader(new File("config.properties"), args));
        Configuration.print(System.out);

        new Application().start();
    }

    private void start() {
        initializeNatives();
        disableControllers();

        try {
            LwjglWindow game = new LwjglWindow("The Realm of Mines");

            AppGameContainer container = new AppGameContainer(game);
            container.setDisplayMode(1920, 1080, true);
            container.setShowFPS(true);
            container.start();
        } catch (SlickException ex) {
            Logger.getLogger(LwjglWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initializeNatives() {
        String path = Application.class.getClassLoader().getResource("natives").getFile();
        System.setProperty("org.lwjgl.librarypath", path);
    }
}
