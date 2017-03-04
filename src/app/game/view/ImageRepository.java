package app.game.view;

import app.game.Load;
import app.game.token.TokenColor;

import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

import static app.game.token.TokenColor.*;

public class ImageRepository {
    public BufferedImage cardBack = get("images/card.png");
    public BufferedImage cardBench = get("images/cards/view/bench.png");
    public BufferedImage cardCastle = get("images/cards/view/castle.png");

    public BufferedImage background1 = get("images/background_1.png");

    private Map<TokenColor, BufferedImage> tokens = loadTokenImages();
    public BufferedImage tokenVersatile = get("images/tokens/versatile.png");

    private Map<TokenColor, BufferedImage> loadTokenImages() {
        Map<TokenColor, BufferedImage> tokens = new EnumMap<>(TokenColor.class);
        tokens.put(Green, get("images/tokens/green.png"));
        tokens.put(Purple, get("images/tokens/purple.png"));
        tokens.put(Blue, get("images/tokens/blue.png"));
        tokens.put(Black, get("images/tokens/black.png"));
        tokens.put(Red, get("images/tokens/red.png"));
        return tokens;
    }

    private BufferedImage get(String filename) {
        return Load.image(filename);
    }

    public BufferedImage getTokenImage(TokenColor color) {
        if (color == null) {
            return tokenVersatile;
        }
        return tokens.get(color);
    }
}
