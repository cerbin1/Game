package app.view.render;

import app.view.ImageRepository;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NobilityRenderer extends Renderer {
    private final BufferedImage nobility;

    public NobilityRenderer(NobilityVO nobilityVO) {
        super(nobilityVO);
        nobility = ImageRepository.imageRepository().nobility;
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(nobility, 0, 0, null);
    }
}
