package game;

import java.util.Random;

class MockRandom extends Random {
    private int index;
    private int[] array;

    MockRandom(int... array) {
        this.array = array;
    }

    @Override
    public int nextInt(int number) {
        index++;
        return array[index];
    }
}
