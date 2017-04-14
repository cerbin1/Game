package app.view;

import java.util.Scanner;

public class Resolution {
    private final int width, height;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Resolution parseResolution(String resolution) {
        Scanner scanner = new Scanner(resolution.replace('x', ' '));
        return new Resolution(scanner.nextInt(), scanner.nextInt());
    }
}
