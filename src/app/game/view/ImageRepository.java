package app.game.view;

import app.game.Load;

import java.awt.image.BufferedImage;

public class ImageRepository {
    public BufferedImage cardBack = get("images/card.png");
    public BufferedImage cardBench = get("images/cards/view/bench.png");
    public BufferedImage cardCastle = get("images/cards/view/castle.png");

    public BufferedImage background1 = get("images/background_1.png");

    public BufferedImage tokenGreen = get("images/tokens/green.png");
    public BufferedImage tokenPurple = get("images/tokens/purple.png");
    public BufferedImage tokenBlue = get("images/tokens/blue.png");
    public BufferedImage tokenBlack = get("images/tokens/black.png");
    public BufferedImage tokenRed = get("images/tokens/red.png");
    public BufferedImage tokenVersatile = get("images/tokens/versatile.png");

    private BufferedImage get(String filename) {
        return Load.image(filename);
    }
}
