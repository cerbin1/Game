package app.view.util;

import org.newdawn.slick.TrueTypeFont;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;

public class Font {
    public static TrueTypeFont POINTS = new TrueTypeFont(new java.awt.Font("Franklin Gothic Medium", BOLD, 52), true);
    public static TrueTypeFont COST = new TrueTypeFont(new java.awt.Font("Franklin Gothic Medium", PLAIN, 40), true);
    public static TrueTypeFont BUTTON_FONT = new TrueTypeFont(new java.awt.Font("Arial", PLAIN, 52), true);
}
