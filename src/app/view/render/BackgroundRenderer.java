package app.view.render;

import java.awt.*;
import java.awt.image.BufferedImage;

import static app.view.ImageRepository.imageRepository;

public class BackgroundRenderer extends Renderer {
    private final BufferedImage background;

    public BackgroundRenderer() {
        super(new ViewObject(0, 0, 0, 0));
        background = imageRepository().background1;
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(background, 0, 0, null);
    }
}
