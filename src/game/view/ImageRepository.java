package game.view;

import game.Load;

import java.awt.image.BufferedImage;

public class ImageRepository {
    public BufferedImage cardBack = get("images/card.png");
    public BufferedImage cardBench = get("images/cards/view/bench.png");
    public BufferedImage cardCastle = get("images/cards/view/castle.png");

    public BufferedImage background1 = get("images/background_1.png");
    public BufferedImage tokens = get("images/tokens.png");

    private BufferedImage get(String filename) {
        return Load.image(filename);
    }
}
