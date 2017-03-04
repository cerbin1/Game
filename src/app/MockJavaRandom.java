package app;

public class MockJavaRandom extends java.util.Random {
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
