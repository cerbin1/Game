package game;

class Tokens {
    private int green;
    private int white;
    private int blue;
    private int black;
    private int red;

    Tokens(int green, int white, int blue, int black, int red, int yellow) {
        this.green = green;
        this.white = white;
        this.blue = blue;
        this.black = black;
        this.red = red;
        this.yellow = yellow;
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

    private int yellow;
}
