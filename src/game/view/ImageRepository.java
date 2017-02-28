package game.view;

import game.Load;

import java.awt.image.BufferedImage;

public class ImageRepository {
    public BufferedImage card = get("images/card.png");
    public BufferedImage background1 = get("images/background_1.png");
    public BufferedImage tokens = get("images/tokens.png");

    private BufferedImage get(String filename) {
        return Load.image(filename);
    }
}
