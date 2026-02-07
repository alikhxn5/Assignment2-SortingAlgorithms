package sorting;

/**
 * Shell sort implementation.
 *
 * Shell sort is a generalization of insertion sort that allows the
 * exchange of elements that are far apart.  It starts with a large
 * gap and reduces the gap over time, performing a gapped insertion
 * sort for each gap.  The choice of gap sequence has a big impact on
 * performance; here we use the simple sequence gap = n/2, n/4,
 * n/8, ... until gap becomes 1.
 */
public class ShellSort implements SortingAlgorithm {
	/**
	 * Sort the array using shell sort.  Sorting is done in place.
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
		// Start with a large gap, then reduce the gap
		for (int gap = n / 2; gap > 0; gap /= 2) {
			// Perform a gapped insertion sort for this gap size.
			for (int i = gap; i < n; i++) {
				int temp = input[i];
				int j = i;
				// Shift earlier gap‑sorted elements up until the correct
				// location for input[i] is found.
				while (j >= gap && input[j - gap] > temp) {
					input[j] = input[j - gap];
					j -= gap;
				}
				// Place temp (the original a[i]) in its correct location.
				input[j] = temp;
			}
		}
		return input;
	}
}