import java.util.concurrent.*;

class BinarySearchTask implements Callable<Integer> {
    private final int[] arr;
    private int target, left, right;

    public BinarySearchTask(int[] arr, int target, int left, int right) {
        this.arr = arr;
        this.target = target;
        this.left = left;
        this.right = right;
    }

    @Override
    public Integer call() {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }
}

public class MultiThreadedBinarySearch {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 }; // Sorted array
        int target = 7; // Element to search
        int n = arr.length;

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Split search space into two halves
        Future<Integer> leftHalf = executor.submit(new BinarySearchTask(arr, target, 0, n / 2));
        Future<Integer> rightHalf = executor.submit(new BinarySearchTask(arr, target, n / 2 + 1, n - 1));

        // Get results
        int leftResult = leftHalf.get();
        int rightResult = rightHalf.get();

        // Determine the final result
        int result = (leftResult != -1) ? leftResult : rightResult;
        System.out.println((result != -1) ? "Element found at index: " + result : "Element not found");

        executor.shutdown();
    }
}
