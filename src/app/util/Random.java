package app.util;

public class Random {
    private java.util.Random random;

    public Random() {
        this(new java.util.Random());
    }

    public Random(java.util.Random random) {
        this.random = random;
    }

    public int nextInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
