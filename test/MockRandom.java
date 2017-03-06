class MockRandom extends app.util.Random {
    private int index;
    private int[] array;

    MockRandom(int... array) {
        this.array = array;
    }

    public int nextInt(int min, int max) {
        try {
            return array[index++];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new RuntimeException("Not enough values specified for MockRandom");
        }
    }
}
