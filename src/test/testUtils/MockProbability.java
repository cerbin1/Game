package testUtils;

import app.util.Probability;

public class MockProbability extends Probability {
    private int index;
    private int[] array;

    public MockProbability(int... array) {
        this.array = array;
    }

    public int nextInt(int min, int max) {
        try {
            return array[index++];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new RuntimeException("Not enough values specified for testUtils.MockProbability");
        }
    }
}
