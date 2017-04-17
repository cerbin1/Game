package app;

import app.model.config.Configuration;
import app.model.config.ConfigurationLoader;
import app.presenter.GameWindow;
import app.view.ImageRepository;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.newdawn.slick.Input.disableControllers;

import java.io.File;

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
            LwjglWindow game = new LwjglWindow("Splendor");

            AppGameContainer container = new AppGameContainer(game);
            container.setDisplayMode(1920, 1080, true);
            container.setShowFPS(true);
            container.start();
        } catch (SlickException ex) {
            Logger.getLogger(LwjglWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initializeNatives() {
        String path = LwjglWindow.class.getClassLoader().getResource("natives").getFile();
        System.setProperty("org.lwjgl.librarypath", path);
    }

    class LwjglWindow extends BasicGame {
        GameWindow window;

        private LwjglWindow(String gameName) {
            super(gameName);
            Mouse.setClipMouseCoordinatesToWindow(false);
        }

        @Override
        public void init(GameContainer gc) throws SlickException {
            ImageRepository.load();
            window = new GameWindow();
        }

        @Override
        public void update(GameContainer gc, int delta) throws SlickException {
            window.update(delta / 1000.0);
        }

        @Override
        public void render(GameContainer gc, Graphics graphics) throws SlickException {
            window.render(graphics);
        }

        @Override
        public void mousePressed(int button, int x, int y) {
            window.mouseClicked(Mouse.getX(), -Mouse.getY());
        }
    }
}
