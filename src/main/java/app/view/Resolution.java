package app.view;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
        try {
            Scanner scanner = new Scanner(resolution.replace('x', ' '));
            int width = scanner.nextInt(), height = scanner.nextInt();
            return new Resolution(width, height);
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Invalid resolution");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Invalid resolution");
        }
    }
}
