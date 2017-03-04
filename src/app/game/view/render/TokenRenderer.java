package app.game.view.render;

import app.game.view.ImageRepository;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TokenRenderer extends Renderer {
    private final BufferedImage tokenImage;

    public TokenRenderer(Graphics2D graphics2D, TokenVO tokenVO, ImageRepository imageRepository) {
        this(graphics2D, tokenVO, imageRepository.getTokenImage(tokenVO.getColor()));
    }

    TokenRenderer(Graphics2D graphics2D, TokenVO tokenVO, BufferedImage image) {
        super(tokenVO);
        this.tokenImage = image;
    }

    @Override
    protected void render(Graphics2D graphics) {
        graphics.drawImage(tokenImage, 0, 0, null);
    }
}
