package game;

import java.util.Random;

public class MockRandom extends Random {
    @Override
    public int nextInt(int number) {
        return number;
    }
}
