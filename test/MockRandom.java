import java.util.Random;

class MockRandom extends Random {
    private int index;
    private int[] array;

    MockRandom(int... array) {
        this.array = array;
    }

    @Override
    public int nextInt(int number) {
        return array[index++];
    }
}
