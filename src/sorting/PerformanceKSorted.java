package sorting;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Runs performance tests on k-sorted data (k = 10) and writes a report file.
 * This uses the same sizes and iterations as the random-data test.
 */
public class PerformanceKSorted {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
        int iterations = 20;
        int k = 10;

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

        try (FileWriter writer = new FileWriter("ksorted_performance_report.txt")) {
            for (int idx = 0; idx < algorithms.length; idx++) {
                SortingAlgorithm alg = algorithms[idx];
                String name = names[idx];
                Tester tester = new Tester(alg);

                writer.write("Sorting algorithm - " + name + nl);
                System.out.println("Sorting algorithm - " + name);

                for (int size : sizes) {
                    // No cutoff: run all sizes, even the big ones.
                    tester.ktest(iterations, size, k);
                    double avg = tester.lastAverageMs();
                    writer.write("Sorted " + size + " elements in " + avg + " ms (avg)" + nl);
                }

                writer.write(nl);
            }
            System.out.println("K-sorted performance report written to ksorted_performance_report.txt");
        } catch (IOException e) {
            System.err.println("Error writing k-sorted report: " + e.getMessage());
        }
    }
}
