import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

class MergeSortParallel extends RecursiveAction {
    private int[] array;
    private int left, right;

    public MergeSortParallel(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        if (left >= right)
            return;

        int mid = (left + right) / 2;

        // Create two subtasks for sorting halves
        MergeSortParallel leftTask = new MergeSortParallel(array, left, mid);
        MergeSortParallel rightTask = new MergeSortParallel(array, mid + 1, right);

        // Fork both subtasks (execute in parallel)
        invokeAll(leftTask, rightTask);

        // Merge the sorted halves
        merge(left, mid, right);
    }

    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            array[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }

        while (i < n1)
            array[k++] = L[i++];
        while (j < n2)
            array[k++] = R[j++];
    }

    public static void parallelMergeSort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new MergeSortParallel(array, 0, array.length - 1));
    }
}

public class ParallelMergeSortExample {
    public static void main(String[] args) {
        int[] array = { 38, 27, 43, 3, 9, 82, 10 };

        System.out.println("Original array:");
        printArray(array);

        // Perform parallel merge sort
        MergeSortParallel.parallelMergeSort(array);

        System.out.println("\nSorted array:");
        printArray(array);
    }

    public static void printArray(int[] array) {
        for (int num : array)
            System.out.print(num + " ");
        System.out.println();
    }
}
