package app;

public class MockRandom extends app.util.Random {
    private int index;
    private int[] array;

    public MockRandom(int... array) {
        this.array = array;
    }

    public int nextInt(int min, int max) {
        return array[index++];
    }
}
