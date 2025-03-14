package remainingQuestions;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null)
                pq.offer(node);
        }

        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll(); // Get the smallest node
            tail.next = minNode;
            tail = tail.next;
            if (minNode.next != null) {
                pq.offer(minNode.next); // Push the next node of the extracted node
            }
        }
        return dummy.next;
    }
}
