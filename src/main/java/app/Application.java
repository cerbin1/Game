package app;

import app.presenter.GameWindow;
import org.newdawn.slick.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {
    public static void main(String[] args) {
        try {
            LwjglWindow game = new LwjglWindow("Simple Slick Game");

            AppGameContainer container = new AppGameContainer(game);
            container.setDisplayMode(1920, 1080, true);
            container.start();
        } catch (SlickException ex) {
            Logger.getLogger(LwjglWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static class LwjglWindow extends BasicGame {
        GameWindow window;

        private LwjglWindow(String gameName) {
            super(gameName);
        }

        @Override
        public void init(GameContainer gc) throws SlickException {
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
