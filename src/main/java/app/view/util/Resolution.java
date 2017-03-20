package app.view.util;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.awt.Toolkit.getDefaultToolkit;

public class Resolution {
    private final static Dimension screenSize = getDefaultToolkit().getScreenSize();

    public static void scaleFullHdToResolution(AffineTransform transform) {
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        transform.scale(width / 1920.0, height / 1080);
    }
}
