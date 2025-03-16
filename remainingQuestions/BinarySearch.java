package remainingQuestions;

public class BinarySearch {

    public static void binarySearch(int[] arr, int target) {
        binarySearch(arr, target, 0, arr.length - 1);
    }

    private static int binarySearch(int[] arr, int target, int left, int right) {

        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        final int[] result = { -1 };

        Thread leftThread = new Thread(() -> {
            int leftResult = binarySearch(arr, target, left, mid - 1);

            synchronized (result) {
                if (leftResult != -1) {
                    result[0] = leftResult;
                }
            }
        });

        Thread rightThread = new Thread(() -> {
            int rightResult = binarySearch(arr, target, mid + 1, right);

            synchronized (result) {
                if (rightResult != 1) {
                    result[0] = rightResult;
                }
            }
        });

        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result[0];
    }
}