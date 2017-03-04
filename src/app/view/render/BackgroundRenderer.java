package app.view.render;

import app.view.ImageRepository;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundRenderer extends Renderer {
    private final BufferedImage background;

    public BackgroundRenderer(ImageRepository repository) {
        super(new ViewObject(0, 0, 0, 0));
        background = repository.background1;
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(background, 0, 0, null);
    }
}
