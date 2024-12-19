
// Java class for Merge Sort
import java.io.*;
import java.util.Arrays;
import java.util.concurrent.*;

public class MergeSort {

    // Merges two subarrays of arr[].
    public void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using merge()
    public void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    // A utility function to print array of size n
    public void printArray(int arr[], int n) {
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // RecursiveTask for concurrent merge sort
    private static class MergeSortTask extends RecursiveTask<Void> {
        private final int[] arr;
        private final int l;
        private final int r;
        private final MergeSort mergeSort;

        public MergeSortTask(int[] arr, int l, int r, MergeSort mergeSort) {
            this.arr = arr;
            this.l = l;
            this.r = r;
            this.mergeSort = mergeSort;
        }

        @Override
        protected Void compute() {
            if (l < r) {
                int m = l + (r - l) / 2;

                MergeSortTask leftTask = new MergeSortTask(arr, l, m, mergeSort);
                MergeSortTask rightTask = new MergeSortTask(arr, m + 1, r, mergeSort);

                invokeAll(leftTask, rightTask);

                mergeSort.merge(arr, l, m, r);
            }
            return null;
        }
    }

    // Concurrent sort method
    public void sortConcurrent(int[] arr, int numThreads) {
        ForkJoinPool pool = new ForkJoinPool(numThreads);
        MergeSortTask task = new MergeSortTask(arr, 0, arr.length - 1, this);
        pool.invoke(task);
        pool.shutdown();
    }
}