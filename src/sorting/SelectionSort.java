package sorting;

/**
 * Selection sort implementation.
 *
 * Selection sort repeatedly selects the smallest remaining element
 * from the unsorted portion of the array and swaps it with the
 * first unsorted element.  Like bubble and insertion sort, it
 * operates in O(n^2) time and is generally inefficient on large
 * arrays.  However, it performs a minimal number of swaps compared
 * to bubble sort.
 */
public class SelectionSort implements SortingAlgorithm {
	/**
	 * Sort the array using selection sort.  Sorting is done in place.
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
		// Iterate over each position in the array where the next smallest
		// element should be placed.
		for (int i = 0; i < n - 1; i++) {
			// Assume the element at i is the smallest.
			int minIndex = i;
			// Find the actual smallest element in the unsorted portion.
			for (int j = i + 1; j < n; j++) {
				if (input[j] < input[minIndex]) {
					minIndex = j;
				}
			}
			// Swap the smallest element into position i.
			if (minIndex != i) {
				int temp = input[i];
				input[i] = input[minIndex];
				input[minIndex] = temp;
			}
		}
		return input;
	}
}