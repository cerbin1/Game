package game;

public class Tokens {
    private final int green, purple, blue, black, red;
    private final int versatile;

    public Tokens() {
        this(0, 0);
    }

    public Tokens(int commonTokensCount, int rareTokensCount) {
        this.green = commonTokensCount;
        this.purple = commonTokensCount;
        this.blue = commonTokensCount;
        this.black = commonTokensCount;
        this.red = commonTokensCount;
        this.versatile = rareTokensCount;
    }

    public Tokens(int green, int purple, int blue, int black, int red) {
        this.green = green;
        this.purple = purple;
        this.blue = blue;
        this.black = black;
        this.red = red;
        this.versatile = 0;
    }

    public int getGreen() {
        return green;
    }

    public int getPurple() {
        return purple;
    }

    public int getBlue() {
        return blue;
    }

    public int getBlack() {
        return black;
    }

    public int getRed() {
        return red;
    }

    public int getVersatile() {
        return versatile;
    }
}
