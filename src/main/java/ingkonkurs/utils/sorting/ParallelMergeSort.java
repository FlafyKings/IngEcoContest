package ingkonkurs.utils.sorting;

import java.util.concurrent.*;

public class ParallelMergeSort {
    private static final ForkJoinPool pool = createForkJoinPool();
    private static final int THRESHOLD = 40;

    private static ForkJoinPool createForkJoinPool() {
        return new ForkJoinPool();
    }

    public static void shutdownForkJoinPool() {
        pool.shutdown();
    }

    public static <T extends Comparable<T>> void parallelMergeSort(T[] arr, boolean ascending) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[arr.length];
        pool.invoke(new MergeSortTask<>(arr, temp, 0, arr.length - 1, ascending));
    }

    private static class MergeSortTask<T extends Comparable<T>> extends RecursiveAction {

        private final T[] arr;
        private final T[] temp;
        private final int low;
        private final int high;
        private final boolean ascending;

        public MergeSortTask(T[] arr, T[] temp, int low, int high, boolean ascending) {
            this.arr = arr;
            this.temp = temp;
            this.low = low;
            this.high = high;
            this.ascending = ascending;
        }

        @Override
        protected void compute() {
            if (low < high) {
                if (high - low < THRESHOLD) {
                    insertionSort(arr, low, high, ascending);
                }else {
                    int mid = (low + high) / 2;
                    MergeSortTask<T> leftTask = new MergeSortTask<>(arr, temp, low, mid, ascending);
                    MergeSortTask<T> rightTask = new MergeSortTask<>(arr, temp, mid + 1, high, ascending);
                    invokeAll(leftTask, rightTask);
                    merge(arr, temp, low, mid, high, ascending);
                }
            }
        }

        private <O extends Comparable<O>> void merge(O[] arr, O[] temp, int low, int mid, int high, boolean ascending) {
            int i = low;
            int j = mid + 1;
            int k = low;

            while (i <= mid && j <= high) {
                if ((arr[i].compareTo(arr[j]) >= 0) == ascending) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }

            }
            while (i <= mid) {
                temp[k++] = arr[i++];
            }

            while (j <= high) {
                temp[k++] = arr[j++];
            }

            for (i = low; i <= high; i++) {
                arr[i] = temp[i];
            }
        }

        private static <T extends Comparable<T>> void insertionSort(T[] arr, int low, int high, boolean ascending) {
            for (int i = low + 1; i <= high; i++) {
                int j = i - 1;
                T current = arr[i];

                while (j >= low && (arr[j].compareTo(current) <= 0) == ascending) {
                    arr[j + 1] = arr[j];
                    j--;
                }

                arr[j + 1] = current;
            }
        }
    }
}


