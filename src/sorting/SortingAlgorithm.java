package sorting;

/**
 * Interface for all sorting algorithm implementations in this assignment.
 *
 * A sorting algorithm takes an array of integers and returns the same
 * array sorted in non‑decreasing order.  All implementations should
 * sort the array in place and then return it.  The method name is
 * intentionally called {@code sorty} (rather than the more common
 * {@code sort}) to match the specification provided in the assignment.
 */
public interface SortingAlgorithm {
	/**
	 * Sort the given array of integers in non‑decreasing order.
	 *
	 * @param input the array to sort; may be modified by the algorithm
	 * @return the same array instance, now sorted
	 */
	int[] sorty(int[] input);
}