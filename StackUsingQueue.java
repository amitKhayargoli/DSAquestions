import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {

    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    public void push(int value) {

        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        q1.add(value);

        while (!q2.isEmpty()) {
            q1.add(q2.remove());
        }

    }

    public int pop() {
        return q1.remove();
    }

    public static void main(String[] args) {

        StackUsingQueue stack = new StackUsingQueue();
        stack.push(1);
        stack.push(2);

        System.out.println(stack.pop());
    }
}
