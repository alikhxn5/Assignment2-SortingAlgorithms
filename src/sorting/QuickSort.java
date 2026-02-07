package sorting;

/**
 * Quick sort implementation.
 *
 * Quick sort is a divide‑and‑conquer algorithm: choose a pivot,
 * partition the array around the pivot so that all elements on the
 * left are less than or equal to the pivot and all elements on the
 * right are greater, then recursively sort the two halves.  In the
 * average case quicksort runs in O(n log n) time, but a poor pivot
 * choice can degrade it to O(n^2).  Using a better pivot selection
 * strategy can help avoid worst‑case behavior on sorted or nearly
 * sorted input.
 */
public class QuickSort implements SortingAlgorithm {
	/**
	 * Sort the array using quicksort.  Sorting is done in place.
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
		quicksort(input, 0, input.length - 1);
		return input;
	}

	/**
	 * Recursively sorts the subarray a[lo..hi] using quicksort.
	 *
	 * Base case: if lo >= hi the subarray has zero or one element and
	 * is already sorted.
	 *
	 * We use a median‑of‑three pivot selection to reduce the chance
	 * of hitting worst‑case performance on sorted or nearly sorted
	 * data.  After choosing a pivot, we partition the array into
	 * elements <= pivot and elements > pivot, then recurse on the
	 * two partitions.
	 */
	private void quicksort(int[] a, int lo, int hi) {
		// Base case: nothing to sort
		if (lo >= hi) {
			return;
		}
		// Choose a pivot index using the median of a[lo], a[mid], a[hi].
		int mid = lo + (hi - lo) / 2;
		int pivotIndex = medianOfThreeIndex(a, lo, mid, hi);
		// Move pivot to the end (hi) so partitioning is easier.
		swap(a, pivotIndex, hi);
		// Partition the array around the pivot; returns final pivot location
		int p = partition(a, lo, hi);
		// Recursively sort the two subarrays on either side of the pivot.
		// We must exclude the pivot itself (p) from further sorting or
		// else the recursion may never make progress.
		quicksort(a, lo, p - 1);
		quicksort(a, p + 1, hi);
	}

	/**
	 * Partition the subarray a[lo..hi] using Lomuto's partition scheme.
	 *
	 * The pivot is stored at a[hi].  We walk through the array with
	 * index j, keeping track of the position i where the next element
	 * less than or equal to the pivot should go.  When we encounter
	 * such an element, we swap it into position i and increment i.
	 *
	 * After scanning, we swap the pivot into its final position i.
	 * Then all elements left of i are <= pivot and all to the right
	 * are > pivot.  We return i so quicksort can recurse on the two
	 * sides.
	 */
	private int partition(int[] a, int lo, int hi) {
		int pivot = a[hi];
		int i = lo;
		for (int j = lo; j < hi; j++) {
			if (a[j] <= pivot) {
				swap(a, i, j);
				i++;
			}
		}
		// Place pivot between the partitions
		swap(a, i, hi);
		return i;
	}

	/**
	 * Returns the index of the median (middle) value among a[i], a[j], a[k].
	 *
	 * This helper is used to choose a pivot that is unlikely to be
	 * extremely small or extremely large, which helps avoid worst‑case
	 * recursion depth on sorted or nearly sorted inputs.
	 */
	private int medianOfThreeIndex(int[] a, int i, int j, int k) {
		int ai = a[i];
		int aj = a[j];
		int ak = a[k];
		// Compare values pairwise to find the median index
		if (ai < aj) {
			if (aj < ak) {
				return j; // ai < aj < ak
			}
			return (ai < ak) ? k : i; // ai < ak <= aj or ak <= ai < aj
		} else {
			if (ai < ak) {
				return i; // aj <= ai < ak
			}
			return (aj < ak) ? k : j; // aj < ak <= ai or ak <= aj <= ai
		}
	}

	/**
	 * Swap the values at indices x and y in array a.
	 */
	private void swap(int[] a, int x, int y) {
		if (x == y) return;
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
}