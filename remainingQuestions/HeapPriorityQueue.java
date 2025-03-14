package remainingQuestions;

class MinHeapPriorityQueue {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the heap
    public MinHeapPriorityQueue(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    // Insert element into the priority queue
    public void insert(int val) {
        if (size == capacity) {
            throw new RuntimeException("Heap is full");
        }
        heap[size] = val;
        size++;
        heapifyUp(size - 1);
    }

    // Remove the root element (highest priority)
    public int remove() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return root;
    }

    // Peek the root element without removing it
    public int peek() {
        if (size == 0) {
            throw new RuntimeException("Heap is empty");
        }
        return heap[0];
    }

    // Helper method to restore heap property after insertion
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;
        if (index > 0 && heap[index] < heap[parent]) {
            swap(index, parent);
            heapifyUp(parent);
        }
    }

    // Helper method to restore heap property after removal
    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        if (leftChild < size && heap[leftChild] < heap[smallest]) {
            smallest = leftChild;
        }
        if (rightChild < size && heap[rightChild] < heap[smallest]) {
            smallest = rightChild;
        }
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    // Helper method to swap two elements
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
