class HeapifyExample {
    // Function to heapify a subtree rooted at index i in an array of size n
    static void maxHeapify(int arr[], int n, int root) {
        int largest = root; // Assume root is the largest
        int left = 2 * root + 1; // Left child index
        int right = 2 * root + 2; // Right child index

        // Check if left child exists and is greater than root
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // Check if right child exists and is greater than the largest so far
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // If largest is not root, swap and continue heapifying
        if (largest != root) {
            int temp = arr[root];
            arr[root] = arr[largest];
            arr[largest] = temp;

            // Recursively heapify the affected subtree
            maxHeapify(arr, n, largest);
        }
    }

    static void printArray(int arr[]) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = { 4, 10, 3, 5, 1 };
        int n = arr.length;

        System.out.println("Original array:");
        printArray(arr);

        maxHeapify(arr, n, 0);

        System.out.println("Heapified array:");
        printArray(arr);
    }
}
