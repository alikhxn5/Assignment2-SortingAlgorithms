package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * Helper for generating k-sorted arrays.
 * k-sorted means each element is at most k positions away from its sorted spot.
 * For the assignment we use k = 10 by default.
 */
public class KSorted {
    private static final Random RNG = new Random();

    /**
     * Fill the array with 10-sorted data.
     */
    public static void generateKSorted(int[] a) {
        generateKSorted(a, 10);
    }

    /**
     * Fill the array with k-sorted data.
     * This uses a simple block shuffle so elements move at most k positions.
     */
    public static void generateKSorted(int[] a, int k) {
        if (a == null) {
            return;
        }
        if (a.length < 2) {
            return;
        }
        if (k < 0) {
            k = 0;
        }

        // Step 1: fill with random ints.
        for (int i = 0; i < a.length; i++) {
            a[i] = RNG.nextInt();
        }

        // Step 2: fully sort so we know the correct order.
        Arrays.sort(a);

        // Step 3: shuffle each block of size (k + 1).
        int blockSize = k + 1;
        for (int start = 0; start < a.length; start += blockSize) {
            int end = Math.min(a.length, start + blockSize);
            for (int i = end - 1; i > start; i--) {
                int j = start + RNG.nextInt(i - start + 1);
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
    }
}
