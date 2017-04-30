package app.view.render.renderer;

import app.view.render.vo.ButtonVO;
import app.view.util.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import static app.view.ImageRepository.imageRepository;

public class ButtonRenderer extends Renderer {
    private final Image buttonImage;
    private final ButtonVO buttonVO;

    public ButtonRenderer(ButtonVO buttonVO) {
        super(buttonVO);
        this.buttonVO = buttonVO;
        buttonImage = imageRepository().button;
    }

    @Override
    protected void render(Graphics graphics) {
        graphics.scale(1.6f, 1.6f);
        graphics.drawImage(buttonImage, 0, 0, null);
        graphics.scale(1 / 1.6f, 1 / 1.6f);
        graphics.setFont(Font.BUTTON_FONT);
        graphics.drawString(buttonVO.getTitle(), 50, 30);
    }
}
