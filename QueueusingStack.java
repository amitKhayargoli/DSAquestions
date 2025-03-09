import java.util.Stack;

class QueueusingStack {

    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    // Enqueue operation

    // Constant time complexity O(1) time
    void enqueue(int data) {
        s1.push(data);
    }

    int dequeue() {
        if (s1.isEmpty() && s2.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }

        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }

        }
        return s2.pop();

    }

    // Main Method

    public static void main(String[] args) {

        QueueusingStack queue = new QueueusingStack();
        queue.enqueue(1);
        queue.enqueue(6);
        queue.enqueue(9);

        System.out.println("Dequeued element: " + queue.dequeue());

    }
}

// Algorithm

// Queue using stack

// Create two empty stacks s1 and s2
// Enqueue operation -> s1.push(data)

// Dequeue operation

// If stack 2 is empty then
// Push all elements of s1 to s2 then return the first element or //S2.pop()
