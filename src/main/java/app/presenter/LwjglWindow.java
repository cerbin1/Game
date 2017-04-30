package app.presenter;

import app.view.ImageRepository;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class LwjglWindow extends BasicGame {
    private GameWindow window;

    public LwjglWindow(String gameName) {
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
