import java.util.Random;

public class SortingExperiment {

    private static final Random RANDOM = new Random();

    public static void runExperiment() {
        int[] sizes = {20, 50, 100, 500};

        System.out.println("=====================================================================");
        System.out.printf("%-15s | %-10s | %-18s | %-15s%n", "Algorithm", "Size", "Comparisons", "Time (ns)");
        System.out.println("=====================================================================");

        for (int size : sizes) {
            int[] original = generateRandomArray(size);
            runOneAlgorithm("Selection Sort", original, size, 0);
            runOneAlgorithm("Insertion Sort", original, size, 1);
            runOneAlgorithm("Merge Sort", original, size, 2);
            runOneAlgorithm("Quick Sort", original, size, 3);
        }

        System.out.println();
        System.out.println("=== Additional test: almost-sorted array of 100 elements ===");
        int[] almostSorted = buildAlmostSortedArray(100);
        System.out.println("=====================================================================");
        System.out.printf("%-15s | %-10s | %-18s | %-15s%n", "Algorithm", "Size", "Comparisons", "Time (ns)");
        System.out.println("=====================================================================");
        runOneAlgorithm("Selection Sort", almostSorted, 100, 0);
        runOneAlgorithm("Insertion Sort", almostSorted, 100, 1);
        runOneAlgorithm("Merge Sort", almostSorted, 100, 2);
        runOneAlgorithm("Quick Sort", almostSorted, 100, 3);
    }

    private static void runOneAlgorithm(String name, int[] source, int size, int algoId) {
        int[] copy = copyArray(source);

        long startTime = System.nanoTime();
        long comparisons;
        switch (algoId) {
            case 0: comparisons = SortAlgorithms.selectionSort(copy); break;
            case 1: comparisons = SortAlgorithms.insertionSort(copy); break;
            case 2: comparisons = SortAlgorithms.mergeSort(copy); break;
            default: comparisons = SortAlgorithms.quickSortSilent(copy); break;
        }
        long endTime = System.nanoTime();

        System.out.printf("%-15s | %-10d | %-18d | %-15d%n", name, size, comparisons, (endTime - startTime));
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(1000) + 1;
        }
        return arr;
    }

    private static int[] buildAlmostSortedArray(int size) {
        int[] arr = generateRandomArray(size);
        SortAlgorithms.selectionSort(arr);

        Random swapRandom = new Random();
        for (int s = 0; s < 5; s++) {
            int pos = swapRandom.nextInt(size - 1);
            int temp = arr[pos];
            arr[pos] = arr[pos + 1];
            arr[pos + 1] = temp;
        }
        return arr;
    }

    private static int[] copyArray(int[] src) {
        int[] copy = new int[src.length];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i];
        }
        return copy;
    }
}