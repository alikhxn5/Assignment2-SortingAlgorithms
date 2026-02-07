package sorting;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Runs performance tests for all sorting algorithms and writes a report file.
 * I run every size for every algorithm (no cutoff), even if it is slow.
 */
public class Performance {
    /**
     * Main entry point for random-data performance testing.
     */
    public static void main(String[] args) {
        // Sizes from the assignment prompt.
        int[] sizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
        int iterations = 20;

        SortingAlgorithm[] algorithms = {
            new BubbleSort(),
            new InsertionSort(),
            new SelectionSort(),
            new ShellSort(),
            new QuickSort(),
            new MergeSort()
        };
        String[] names = {
            "Bubble sort", "Insertion sort", "Selection sort",
            "Shell sort", "Quick sort", "Merge sort"
        };

        String nl = System.lineSeparator();

        // Write results to a text file.
        try (FileWriter writer = new FileWriter("performance_report.txt")) {
            for (int idx = 0; idx < algorithms.length; idx++) {
                SortingAlgorithm alg = algorithms[idx];
                String name = names[idx];
                Tester tester = new Tester(alg);

                // Header so we know which algorithm the results belong to.
                writer.write("Sorting algorithm - " + name + nl);
                System.out.println("Sorting algorithm - " + name);

                for (int size : sizes) {
                    // No cutoff: run all sizes, even the big ones.
                    tester.test(iterations, size);
                    double avg = tester.lastAverageMs();
                    writer.write("Sorted " + size + " elements in " + avg + " ms (avg)" + nl);
                }

                writer.write(nl);
            }
            System.out.println("Performance report written to performance_report.txt");
        } catch (IOException e) {
            System.err.println("Error writing report: " + e.getMessage());
        }
    }
}
