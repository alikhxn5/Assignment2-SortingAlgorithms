package sorting;

/**
 * Merge sort implementation.
 *
 * Merge sort is a classic divide‑and‑conquer sorting algorithm with
 * guaranteed O(n log n) running time.  It divides the array into
 * halves, recursively sorts each half, and then merges the two
 * sorted halves into a single sorted array.  Merge sort is stable
 * (preserves equal element order) and works well on large data sets.
 */
public class MergeSort implements SortingAlgorithm {
	/**
	 * Sort the array using merge sort.  Sorting is done in place
	 * but requires additional memory for merging.
	 *
	 * @param input the array to sort
	 * @return the sorted array (same instance as input)
	 */
	@Override
	public int[] sorty(int[] input) {
		if (input == null) {
			return null;
		}
		if (input.length < 2) {
			return input;
		}
		// Create a temporary array for merging; it's passed down the
		// recursion to avoid allocating many temporary arrays.
		int[] temp = new int[input.length];
		mergesort(input, temp, 0, input.length - 1);
		return input;
	}

	/**
	 * Recursively sorts the subarray a[lo..hi] using merge sort.
	 *
	 * Splits the array in two around mid, sorts each half, then
	 * merges the sorted halves.
	 */
	private void mergesort(int[] a, int[] temp, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		// Recursively sort left and right halves
		mergesort(a, temp, lo, mid);
		mergesort(a, temp, mid + 1, hi);
		// Merge the sorted halves
		merge(a, temp, lo, mid, hi);
	}

	/**
	 * Merge two consecutive sorted subarrays a[lo..mid] and a[mid+1..hi]
	 * into a single sorted array.  Uses the temp array to avoid
	 * repeatedly allocating new arrays during merge operations.
	 */
	private void merge(int[] a, int[] temp, int lo, int mid, int hi) {
		// Copy the relevant slice of a into temp
		for (int i = lo; i <= hi; i++) {
			temp[i] = a[i];
		}
		int left = lo;       // pointer into left half
		int right = mid + 1; // pointer into right half
		int current = lo;    // current index in a to fill
		// While both halves have elements left to merge
		while (left <= mid && right <= hi) {
			// Choose the smaller element from temp and copy it back to a
			if (temp[left] <= temp[right]) {
				a[current] = temp[left];
				left++;
			} else {
				a[current] = temp[right];
				right++;
			}
			current++;
		}
		// Copy any remaining elements from the left half
		while (left <= mid) {
			a[current] = temp[left];
			left++;
			current++;
		}
		// Elements on the right half that remain are already in place
		// because they are greater than all elements in the left half
	}
}