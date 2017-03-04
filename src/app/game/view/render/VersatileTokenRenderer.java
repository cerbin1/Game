package app.game.view.render;

import app.game.view.ImageRepository;

public class VersatileTokenRenderer extends TokenRenderer {
    public VersatileTokenRenderer(TokenVO tokenVO, ImageRepository imageRepository) {
        super(tokenVO, imageRepository.tokenVersatile);
        if (tokenVO.getColor() != null) {
            throw new RuntimeException("Using VersatileRenderer to draw non-versatile token");
        }
    }
}
