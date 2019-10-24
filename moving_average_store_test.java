class MovingAverageStoreTest {
  public static void main(String[] args) {
    testMinimalFunctionality();
    testUpdateMovingAverageSize();
    testGetLast();
    testGetIndex();
  }

  private static void testMinimalFunctionality() {
    DataStore store = new MovingAverageStore(3);
    Double item = (double) 1;

    for (int i = 0; i < 5; i++) {
      store.add(item);
    }

    System.out.println("Expected: 1.0, Actual: " + store.getMovingAverage());
  }

  private static void testUpdateMovingAverageSize() {
    DataStore store = new MovingAverageStore(5);
    for (double i = 1; i < 11; i++) {
      store.add(i);
    }
    // current moving average should be 8
    System.out.println("Expected: 8.0, Actual: " + store.getMovingAverage());

    store.setMovingAverageSize(2);
    // updated average should be 9.5
    System.out.println("Expected: 9.5, Actual: " + store.getMovingAverage());
  }

  private static void testGetLast() {
    DataStore store = new MovingAverageStore(5);
    boolean caughtException = false;
    try {
      store.getLast();
    } catch (IndexOutOfBoundsException e) {
      caughtException = true;
    }
    System.out.println("Expected: true, Actual: " + caughtException);

    for (double i = 9; i < 32; i++) {
      store.add(i);
    }
    System.out.println("Expected: 31.0, Actual: " + store.getLast());
  }

  private static void testGetIndex() {
    DataStore store = new MovingAverageStore(5);
    for (double i = 9; i < 32; i++) {
      store.add(i);
    }

    System.out.println("Expected: 12.0, Actual: " + store.getIndex(3));
    System.out.println("Expected: 9.0, Actual: " + store.getIndex(0));

    boolean caughtException = false;
    try {
      store.getIndex(88);
    } catch (IndexOutOfBoundsException e) {
      caughtException = true;
    }
    System.out.println("Expected: true, Actual: " + caughtException);
  }
}