import java.util.Arrays;

public class SortAlgorithms {


    public static void selectionSortDemo(int[] arr) {
        int n = arr.length;
        long comparisons = 0;
        long swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
            if (i < 3) System.out.println("After pass " + (i + 1) + ": " + Arrays.toString(arr));
        }
        System.out.println("Selection Sort -> comparisons: " + comparisons + ", swaps: " + swaps);
    }

    public static long selectionSort(int[] arr) {
        int n = arr.length;
        long comparisons = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return comparisons;
    }


    public static void insertionSortDemo(int[] arr) {
        int n = arr.length;
        long comparisons = 0;
        long shifts = 0;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                shifts++;
                j--;
            }
            if (j >= 0) comparisons++;
            arr[j + 1] = key;
            if (i <= 3) System.out.println("After pass " + i + ": " + Arrays.toString(arr));
        }
        System.out.println("Insertion Sort -> comparisons: " + comparisons + ", shifts: " + shifts);
    }

    public static long insertionSort(int[] arr) {
        int n = arr.length;
        long comparisons = 0;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                comparisons++;
                arr[j + 1] = arr[j];
                j--;
            }
            if (j >= 0) comparisons++;
            arr[j + 1] = key;
        }
        return comparisons;
    }


    public static void mergeSortDemo(int[] arr) {
        System.out.println("Initial: " + Arrays.toString(arr));
        mergeSortDemoHelper(arr, 0, arr.length - 1, 0);
    }

    private static void mergeSortDemoHelper(int[] arr, int left, int right, int depth) {
        String indent = "  ".repeat(depth);
        if (left >= right) {
            System.out.println(indent + "Base case reached: [" + arr[left] + "]");
            return;
        }
        int mid = (left + right) / 2;
        System.out.println(indent + "Split: " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1))
                + " -> " + Arrays.toString(Arrays.copyOfRange(arr, left, mid + 1))
                + " | " + Arrays.toString(Arrays.copyOfRange(arr, mid + 1, right + 1)));

        mergeSortDemoHelper(arr, left, mid, depth + 1);
        mergeSortDemoHelper(arr, mid + 1, right, depth + 1);

        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (i = left, k = 0; i <= right; i++, k++) arr[i] = temp[k];

        System.out.println(indent + "Merged: " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
    }

    public static long mergeSort(int[] arr) {
        long[] counter = {0};
        mergeSortHelper(arr, 0, arr.length - 1, counter);
        return counter[0];
    }

    private static void mergeSortHelper(int[] arr, int left, int right, long[] counter) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSortHelper(arr, left, mid, counter);
        mergeSortHelper(arr, mid + 1, right, counter);
        merge(arr, left, mid, right, counter);
    }

    private static void merge(int[] arr, int left, int mid, int right, long[] counter) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            counter[0]++;
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }


    public static void quickSortDemo(int[] arr) {
        System.out.println("Initial: " + Arrays.toString(arr));
        int[] partitionsPrinted = {0};
        quickSortHelper(arr, 0, arr.length - 1, new long[]{0}, partitionsPrinted, true);
        System.out.println("Quick Sort completed.");
    }

    public static long quickSort(int[] arr) {
        long[] counter = {0};
        int[] partitionsPrinted = {0};
        quickSortHelper(arr, 0, arr.length - 1, counter, partitionsPrinted, true);
        return counter[0];
    }

    public static long quickSortSilent(int[] arr) {
        long[] counter = {0};
        int[] partitionsPrinted = {2};
        quickSortHelper(arr, 0, arr.length - 1, counter, partitionsPrinted, false);
        return counter[0];
    }

    private static void quickSortHelper(int[] arr, int start, int end, long[] counter, int[] partitionsPrinted, boolean verbose) {
        if (start < end) {
            int pivotIndex = partition(arr, start, end, counter, partitionsPrinted, verbose);
            quickSortHelper(arr, start, pivotIndex - 1, counter, partitionsPrinted, verbose);
            quickSortHelper(arr, pivotIndex + 1, end, counter, partitionsPrinted, verbose);
        }
    }

    private static int partition(int[] arr, int start, int end, long[] counter, int[] partitionsPrinted, boolean verbose) {
        int pivot = start;
        int i = start;
        int j = end;

        while (i < j) {
            while (i < end && arr[i] <= arr[pivot]) { counter[0]++; i++; }
            while (arr[j] > arr[pivot]) { counter[0]++; j--; }
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[pivot];
        arr[pivot] = arr[j];
        arr[j] = temp;

        if (verbose && partitionsPrinted[0] < 2) {
            System.out.println("Partition stage " + (partitionsPrinted[0] + 1) + ":");
            System.out.println("  Pivot = " + temp);
            System.out.println("  Left partition  = " + Arrays.toString(Arrays.copyOfRange(arr, start, j)));
            System.out.println("  Right partition = " + Arrays.toString(Arrays.copyOfRange(arr, j + 1, end + 1)));
            partitionsPrinted[0]++;
        }
        return j;
    }
}