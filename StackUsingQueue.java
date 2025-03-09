import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    public void enqueue(int value) {

        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        q1.add(value);

        while (!q2.isEmpty()) {
            q1.add(q2.remove());
        }

    }

    public int dequeue() {
        return q1.remove();

    }

    public static void main(String[] args) {

        StackUsingQueue stack = new StackUsingQueue();
        stack.enqueue(20);
        stack.enqueue(40);

        System.out.println(stack.dequeue());
    }
}
