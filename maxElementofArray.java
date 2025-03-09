//Algorithm to find the maximum element of an Array

class maxElementofArray {

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;

        // Time complexity: O(n)

    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50 };
        int max = findMax(arr);
        System.out.println("Maximum element in the array is: " + max);
    }
}