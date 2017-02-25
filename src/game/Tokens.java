package game;

class Tokens {
    private int green, white, blue, black, red;
    private int versatile;

    Tokens(int commonTokensCount, int rareTokensCount) {
        this.green = commonTokensCount;
        this.white = commonTokensCount;
        this.blue = commonTokensCount;
        this.black = commonTokensCount;
        this.red = commonTokensCount;
        this.versatile = rareTokensCount;
    }

    public Tokens(int green, int white, int blue, int black, int red) {
        this.green = green;
        this.white = white;
        this.blue = blue;
        this.black = black;
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public int getWhite() {
        return white;
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
