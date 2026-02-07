package sorting;

import java.util.Random;

/**
 * Helper class to time a sorting algorithm.
 * I generate data outside the timer and then time just sorty().
 * This is not a perfect benchmark, but it is fine for class.
 */
public class Tester {
    private final SortingAlgorithm algorithm;
    private final Random random;
    // Keep the last average so Performance can write the same value to a file.
    private double lastAverageMs = Double.NaN;

    /**
     * Constructor for the Tester. Takes the sorting algorithm to test.
     */
    public Tester(SortingAlgorithm sa) {
        this.algorithm = sa;
        this.random = new Random();
    }

    /**
     * Run a single test on random data and return the time in milliseconds.
     */
    public double singleTest(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt();
        }

        long start = System.nanoTime();
        algorithm.sorty(data);
        long end = System.nanoTime();

        return (end - start) / 1_000_000.0;
    }

    /**
     * Run multiple tests and print the average time.
     */
    public void test(int iterations, int size) {
        double total = 0.0;
        for (int i = 0; i < iterations; i++) {
            total += singleTest(size);
        }
        double average = total / iterations;
        lastAverageMs = average;
        System.out.println("Sorted " + size + " elements in " + average + " ms (avg)");
    }

    /**
     * Run a single test on k-sorted data and return the time in milliseconds.
     */
    public double singleKTest(int size, int k) {
        int[] data = new int[size];
        // Generate k-sorted data OUTSIDE the timing section.
        KSorted.generateKSorted(data, k);

        long start = System.nanoTime();
        algorithm.sorty(data);
        long end = System.nanoTime();

        return (end - start) / 1_000_000.0;
    }

    /**
     * Run multiple tests on k-sorted data and print the average time.
     */
    public void ktest(int iterations, int size, int k) {
        double total = 0.0;
        for (int i = 0; i < iterations; i++) {
            total += singleKTest(size, k);
        }
        double average = total / iterations;
        lastAverageMs = average;
        System.out.println("K-sorted data: sorted " + size + " elements in " + average + " ms (avg)");
    }

    /**
     * Return the last average time computed by test() or ktest().
     */
    public double lastAverageMs() {
        return lastAverageMs;
    }
}
