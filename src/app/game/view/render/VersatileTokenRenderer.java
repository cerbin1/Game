package app.game.view.render;

import app.game.view.ImageRepository;

import java.awt.*;

public class VersatileTokenRenderer extends TokenRenderer {
    public VersatileTokenRenderer(Graphics2D graphics2D, TokenVO tokenVO, ImageRepository imageRepository) {
        super(graphics2D, tokenVO, imageRepository.tokenVersatile);
        if (tokenVO.getColor() != null) {
            throw new RuntimeException("Using VersatileRenderer to draw non-versatile token");
        }
    }
}
