package app.game.view.render;

import app.game.view.ImageRepository;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TokenRenderer extends Renderer {
    private final BufferedImage tokenImage;

    TokenRenderer(TokenVO tokenVO, BufferedImage image) {
        super(tokenVO);
        this.tokenImage = image;
    }

    public TokenRenderer(TokenVO tokenVO, ImageRepository imageRepository) {
        super(tokenVO);
        tokenImage = imageRepository.getTokenImage(tokenVO.getColor());
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(tokenImage, 0, 0, null);
    }
}
