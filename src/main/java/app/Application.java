package app;

import app.presenter.GameWindow;
import app.view.ImageRepository;
import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.newdawn.slick.Input.disableControllers;

public class Application {
    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        initializeNatives();
        disableControllers();

        try {
            LwjglWindow game = new LwjglWindow("Simple Slick Game");

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
    }
}
