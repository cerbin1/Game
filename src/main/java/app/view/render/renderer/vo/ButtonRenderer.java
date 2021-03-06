package app.view.render.renderer.vo;

import app.view.render.renderer.Renderer;
import app.view.render.vo.ButtonVO;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import static app.view.ImageRepository.imageRepository;
import static app.view.util.Font.getFont;
import static org.newdawn.slick.Color.darkGray;
import static org.newdawn.slick.Color.white;

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
        setColor(graphics);
        graphics.scale(1.6f, 1.6f);
        graphics.drawImage(buttonImage, 0, 0, null);
        graphics.scale(1 / 1.6f, 1 / 1.6f);
        graphics.setFont(getFont().BUTTON_FONT);
        graphics.drawString(buttonVO.getTitle(), 100, 30);
    }

    private void setColor(Graphics graphics) {
        if (buttonVO.isEnabled()) {
            graphics.setColor(white);
        } else {
            graphics.setColor(darkGray);
        }
    }
}
