package sorting;

/**
 * Bubble sort implementation.
 *
 * This algorithm repeatedly steps through the array and swaps adjacent
 * elements if they are out of order.  Each pass through the array
 * "bubbles" the largest unsorted element to the end of the array.
 *
 * Bubble sort has a worst‑case and average time complexity of O(n^2).
 * It is easy to implement but inefficient on large arrays.  We
 * implement it here anyway because it was explicitly requested in
 * the assignment.
 */
public class BubbleSort implements SortingAlgorithm {
	/**
	 * Sort the array using bubble sort.  Sorting is done in place.
	 *
	 * @param input the array to sort
	 * @return the sorted array (same instance as input)
	 */
	@Override
	public int[] sorty(int[] input) {
		if (input == null) {
			// if null, nothing to sort
			return null;
		}
		int n = input.length;
		// Outer loop controls the number of passes.  After each pass,
		// the largest remaining element is at the end, so we can
		// decrease the range by one.
		for (int i = 0; i < n - 1; i++) {
			// Inner loop performs the adjacent swaps for this pass.
			for (int j = 0; j < n - 1 - i; j++) {
				// Compare adjacent elements; if out of order, swap them.
				if (input[j] > input[j + 1]) {
					int temp = input[j];
					input[j] = input[j + 1];
					input[j + 1] = temp;
				}
			}
		}
		return input;
	}
}