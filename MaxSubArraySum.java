public class MaxSubArraySum {
    public static int findMaxSum(int[] arr) {
        int maxSum = arr[0]; // Start with the first element
        int currentSum = arr[0]; // Keep track of the current subarray sum

        for (int i = 1; i < arr.length; i++) {

            currentSum = Math.max(arr[i], currentSum + arr[i]);

            maxSum = Math.max(maxSum, currentSum);

        }

        return maxSum; // Return the maximum sum found
    }

    public static void main(String[] args) {
        int[] arr = { 4, -1, 2, 1 };
        System.out.println("Maximum Subarray Sum: " + findMaxSum(arr));
    }
}

// O(n)