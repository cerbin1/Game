package testUtils;

import java.util.Random;

public class MockJavaRandom extends Random {
    private int index;
    private int[] array;

    public MockJavaRandom(int... array) {
        this.array = array;
    }

    @Override
    public int nextInt(int max) {
        return array[index++];
    }
}
