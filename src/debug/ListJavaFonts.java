package debug;

import java.awt.*;

public class ListJavaFonts {
    public static void main(String[] args) {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String fonts[] = graphicsEnvironment.getAvailableFontFamilyNames();

        for (String font : fonts) {
            System.out.println(font);
        }
    }

}