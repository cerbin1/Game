package app.model.util;

import java.util.Random;

public class Probability {
    private final Random random;

    public Probability() {
        this(new Random());
    }

    public Probability(Random random) {
        this.random = random;
    }

    public int nextInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
