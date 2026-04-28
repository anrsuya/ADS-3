import java.util.Arrays;
public class Experiment {
    private final Sorter sorter;
    private final Searcher searcher;
    public Experiment(Sorter sorter, Searcher searcher) {
        this.sorter = sorter;
        this.searcher = searcher;
    }
    public long measureSortTime(int[] arr, String type) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        long start = System.nanoTime();
        if (type.equalsIgnoreCase("basic")) {
            sorter.basicSort(copy);
        } else {
            sorter.advancedSort(copy);
        }
        return System.nanoTime() - start;
    }
    public long measureSearchTime(int[] arr, int target) {
        long start = System.nanoTime();
        searcher.search(arr, target);
        return System.nanoTime() - start;
    }
    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000, 5000};
        String[] inputTypes = {"Random", "Sorted"};
        System.out.println("=".repeat(72));
        System.out.println("           SORTING & SEARCHING ALGORITHM PERFORMANCE ANALYSIS");
        System.out.println("=".repeat(72));
        System.out.println("\n>>> SORTING EXPERIMENTS");
        System.out.printf("%-10s %-10s %-22s %-22s%n",
                "Size", "Input", "Insertion Sort (ns)", "Merge Sort (ns)");
        System.out.println("-".repeat(66));
        for (String inputType : inputTypes) {
            for (int size : sizes) {
                int[] arr = sorter.generateRandomArray(size);
                if (inputType.equals("Sorted")) {
                    Arrays.sort(arr);
                }
                long insertionTime = measureSortTime(arr, "basic");
                long mergeTime     = measureSortTime(arr, "advanced");
                System.out.printf("%-10d %-10s %-22d %-22d%n",
                        size, inputType, insertionTime, mergeTime);
            }
            System.out.println();
        }
        System.out.println(">>> SEARCHING EXPERIMENTS (Binary Search on sorted arrays)");
        System.out.printf("%-10s %-20s %-20s %-10s%n",
                "Size", "Target Scenario", "Binary Search (ns)", "Found?");
        System.out.println("-".repeat(62));

        for (int size : sizes) {
            int[] arr = sorter.generateRandomArray(size);
            Arrays.sort(arr);
            int existingTarget = arr[size / 2];
            long timeFound = measureSearchTime(arr, existingTarget);
            int indexFound = searcher.search(arr, existingTarget);
            int missingTarget = -1;
            long timeNotFound = measureSearchTime(arr, missingTarget);
            int indexNotFound = searcher.search(arr, missingTarget);

            System.out.printf("%-10d %-20s %-20d %-10s%n",
                    size, "Target present", timeFound,
                    (indexFound != -1 ? "Yes (idx " + indexFound + ")" : "No"));
            System.out.printf("%-10d %-20s %-20d %-10s%n",
                    size, "Target absent", timeNotFound,
                    (indexNotFound != -1 ? "Yes" : "No"));
        }

        System.out.println("\n" + "=".repeat(72));
        System.out.println("                         ANALYSIS SUMMARY");
        System.out.println("=".repeat(72));
        System.out.println("• Insertion Sort: O(n^2) — fast on small/sorted arrays.");
        System.out.println("• Merge Sort:     O(n log n) — consistently fast on large arrays.");
        System.out.println("• Binary Search:  O(log n) — extremely fast; requires sorted input.");
        System.out.println("=".repeat(72));
    }
}