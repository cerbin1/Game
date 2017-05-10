package app.view.util;

import org.newdawn.slick.TrueTypeFont;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;

public class Font {
    public TrueTypeFont POINTS_FONT = new TrueTypeFont(new java.awt.Font("Arial", BOLD, 52), true);
    public TrueTypeFont COST_FONT = new TrueTypeFont(new java.awt.Font("Franklin Gothic Medium", PLAIN, 40), true);
    public TrueTypeFont BUTTON_FONT = new TrueTypeFont(new java.awt.Font("Arial", PLAIN, 52), true);

    private static Font font;

    public static Font getFont() {
        if (font == null) {
            font = new Font();
        }
        return font;
    }

    public static void setFont(Font font) {
        Font.font = font;
    }
}
