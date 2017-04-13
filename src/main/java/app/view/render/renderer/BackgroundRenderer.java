package app.view.render.renderer;

import app.view.render.vo.ViewObject;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import static app.view.ImageRepository.imageRepository;

public class BackgroundRenderer extends Renderer {
    private final Image background;

    public BackgroundRenderer() {
        super(new ViewObject(0, 0, 0, 0) {
        });
        background = imageRepository().background1;
    }

    @Override
    protected void render(Graphics graphics) {
        graphics.scale(1 / 0.6f, 1 / 0.6f);
        graphics.drawImage(background, 0, 0, null);
    }

    @Override
    public boolean isHoverable() {
        return false;
    }
}
