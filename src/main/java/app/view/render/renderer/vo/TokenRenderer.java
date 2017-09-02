package app.view.render.renderer.vo;

import app.view.render.renderer.Renderer;
import app.view.render.vo.TokenVO;
import org.newdawn.slick.Image;

import static app.view.ImageRepository.imageRepository;

public class TokenRenderer extends Renderer {
    private final Image tokenImage;

    public TokenRenderer(TokenVO tokenVO) {
        this(tokenVO, imageRepository().getTokenImage(tokenVO.getColor()));
    }

    private TokenRenderer(TokenVO tokenVO, Image image) {
        super(tokenVO);
        this.tokenImage = image;
    }

    @Override
    protected void render(org.newdawn.slick.Graphics graphics) {
        graphics.drawImage(tokenImage, 0, 0, null);
    }
}
