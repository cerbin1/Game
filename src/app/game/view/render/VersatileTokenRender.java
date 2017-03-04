package app.game.view.render;

import app.game.view.ImageRepository;

public class VersatileTokenRender extends TokenRenderer {
    public VersatileTokenRender(TokenVO tokenVO, ImageRepository imageRepository) {
        super(tokenVO, imageRepository.tokenVersatile);
    }
}
