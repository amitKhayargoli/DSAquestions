class BinarySearchMultithread {
    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1);
    }

    private static int binarySearch(int[] arr, int target, int left, int right) {
        if (left > right)
            return -1; // Element not found

        int mid = left + (right - left) / 2;

        if (arr[mid] == target)
            return mid; // Target found

        final int[] result = { -1 }; // To store search result

        Thread leftThread = new Thread(() -> {
            int leftResult = binarySearch(arr, target, left, mid - 1);
            synchronized (result) {
                if (leftResult != -1)
                    result[0] = leftResult;
            }
        });

        Thread rightThread = new Thread(() -> {
            int rightResult = binarySearch(arr, target, mid + 1, right);
            synchronized (result) {
                if (rightResult != -1)
                    result[0] = rightResult;
            }
        });

        leftThread.start();
        rightThread.start();

        try {
            leftThread.join();
            rightThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11, 13, 15 };
        int target = 9;

        int index = binarySearch(arr, target);

        if (index != -1)
            System.out.println("Element found at index: " + index);
        else
            System.out.println("Element not found in the array.");
    }
}
