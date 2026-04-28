import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Sorter     sorter     = new Sorter();
        Searcher   searcher   = new Searcher();
        Experiment experiment = new Experiment(sorter, searcher);
        System.out.println("=".repeat(50));
        System.out.println("DEMO — Small Array (10 elements)");
        System.out.println("=".repeat(50));

        int[] small = sorter.generateRandomArray(10);
        System.out.print("Original:       "); sorter.printArray(small);
        int[] smallCopy = Arrays.copyOf(small, small.length);
        sorter.basicSort(smallCopy);
        System.out.print("Insertion Sort: "); sorter.printArray(smallCopy);
        int[] smallCopy2 = Arrays.copyOf(small, small.length);
        sorter.advancedSort(smallCopy2);
        System.out.print("Merge Sort:     "); sorter.printArray(smallCopy2);

        int target = smallCopy2[smallCopy2.length / 2];
        int idx    = searcher.search(smallCopy2, target);

        System.out.println("Binary Search target " + target + " -> index " + idx);
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DEMO — Medium Array (100 elements)");
        System.out.println("=".repeat(50));

        int[] medium     = sorter.generateRandomArray(100);
        long  t1         = System.nanoTime();
        int[] medCopy1   = Arrays.copyOf(medium, medium.length);
        sorter.basicSort(medCopy1);
        long insertionMs = System.nanoTime() - t1;
        long t2      = System.nanoTime();
        int[] medCopy2 = Arrays.copyOf(medium, medium.length);
        sorter.advancedSort(medCopy2);
        long mergeMs = System.nanoTime() - t2;

        System.out.println("Insertion Sort time: " + insertionMs + " ns");
        System.out.println("Merge Sort time:     " + mergeMs     + " ns");
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DEMO — Large Array (1000 elements)");
        System.out.println("=".repeat(50));

        int[] large  = sorter.generateRandomArray(1000);
        long  lt1    = System.nanoTime();
        int[] lgCopy = Arrays.copyOf(large, large.length);
        sorter.basicSort(lgCopy);
        long lgInsert = System.nanoTime() - lt1;
        long  lt2    = System.nanoTime();
        int[] lgCopy2 = Arrays.copyOf(large, large.length);
        sorter.advancedSort(lgCopy2);
        long lgMerge = System.nanoTime() - lt2;

        System.out.println("Insertion Sort time: " + lgInsert + " ns");
        System.out.println("Merge Sort time:     " + lgMerge  + " ns");
        Arrays.sort(large);
        int bigTarget  = large[500];
        long st        = System.nanoTime();
        int  found     = searcher.search(large, bigTarget);
        long searchTime = System.nanoTime() - st;
        System.out.println("Binary Search for " + bigTarget
                + " -> index " + found + " in " + searchTime + " ns");
        System.out.println();
        experiment.runAllExperiments();
    }
}