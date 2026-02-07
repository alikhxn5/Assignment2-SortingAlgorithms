package sorting;

/**
 * Insertion sort implementation.
 *
 * Insertion sort builds the sorted portion of the array one element
 * at a time.  For each element, it scans backwards through the
 * previously sorted elements and inserts the current element into
 * the correct position.  This algorithm runs in O(n^2) time in the
 * worst case (e.g., reverse sorted input) but performs well on
 * nearly sorted data.
 */
public class InsertionSort implements SortingAlgorithm {
	/**
	 * Sort the array using insertion sort.  Sorting is done in place.
	 *
	 * @param input the array to sort
	 * @return the sorted array (same instance as input)
	 */
	@Override
	public int[] sorty(int[] input) {
		if (input == null) {
			return null;
		}
		int n = input.length;
		// We consider the first element to be trivially sorted.  Starting at
		// index 1, we insert each element into the sorted portion to the
		// left of it.
		for (int i = 1; i < n; i++) {
			int key = input[i];
			int j = i - 1;
			// Shift elements of the sorted portion that are greater than
			// the key to the right to make space for the key.  Note the
			// condition j >= 0 ensures we don't go past the beginning.
			while (j >= 0 && input[j] > key) {
				input[j + 1] = input[j];
				j--;
			}
			// Insert the key into its correct location.
			input[j + 1] = key;
		}
		return input;
	}
}