package app.view;

import java.util.Scanner;

public class Size {
    private final int width, height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Size parseSize(String size) {
        Scanner scanner = new Scanner(size.replace('x', ' '));
        return new Size(scanner.nextInt(), scanner.nextInt());
    }
}
