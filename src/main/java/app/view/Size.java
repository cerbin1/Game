package app.view;

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
        return new Size(0,0);
    }
}
