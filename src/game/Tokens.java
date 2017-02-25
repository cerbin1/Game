package game;

class Tokens {
    private int green, white, blue, black, red, yellow;

    Tokens(int commonTokensCount, int rareTokensCount) {
        this.green = commonTokensCount;
        this.white = commonTokensCount;
        this.blue = commonTokensCount;
        this.black = commonTokensCount;
        this.red = commonTokensCount;
        this.yellow = rareTokensCount;
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

    public int getYellow() {
        return yellow;
    }

}
