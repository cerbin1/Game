package app;

import java.util.Random;

public class MockRandom extends Random {
    private int index;
    private int[] array;

    public MockRandom(int... array) {
        this.array = array;
    }

    @Override
    public int nextInt(int number) {
        return array[index++];
    }
}
