package app.view;

import app.model.token.TokenColor;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.EnumMap;
import java.util.Map;

import static app.model.token.TokenColor.*;

public class ImageRepository {
    public static ImageRepository imageRepository;

    public static void load() {
        imageRepository = new ImageRepository();
    }

    public Image cardBack = get("images/card.png");
    public Image cardBench = get("images/cards/view/bench.png");
    public Image cardCastle = get("images/cards/view/castle.png");
    public Image nobility = get("images/nobilities/nobility.png");
    public Image button = get("images/button.png");

    public Image background1 = get("images/background_1.png");

    private Map<TokenColor, Image> tokens = loadTokenImages();
    private Image tokenVersatile = get("images/tokens/versatile.png");

    private Map<TokenColor, Image> loadTokenImages() {
        Map<TokenColor, Image> tokens = new EnumMap<>(TokenColor.class);
        tokens.put(Green, get("images/tokens/green.png"));
        tokens.put(Purple, get("images/tokens/purple.png"));
        tokens.put(Blue, get("images/tokens/blue.png"));
        tokens.put(Black, get("images/tokens/black.png"));
        tokens.put(Red, get("images/tokens/red.png"));
        return tokens;
    }

    private Image get(String filename) {
        try {
            return new Image(filename);
        } catch (SlickException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Image getTokenImage(TokenColor color) {
        if (color == null) {
            return tokenVersatile;
        }
        return tokens.get(color);
    }

    public static ImageRepository imageRepository() {
        return imageRepository;
    }
}
