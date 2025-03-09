import java.util.HashMap;

public class LRUCaching {

    public static class Node {
        int k, v;
        Node prev, next;

        Node(int k, int v) {
            this.k = k;
            this.v = v;
            this.prev = this.next = null;
        }
    }

    int capacity;
    HashMap<Integer, Node> map;
    Node dummyhead, dummytail;

    LRUCaching(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        dummyhead = new Node(0, 0);
        dummytail = new Node(0, 0);
        dummyhead.next = dummytail;
        dummytail.prev = dummyhead;
    }

    void put(int k, int v) {
        if (map.containsKey(k)) {
            removeNode(map.get(k));
        } else if (map.size() == capacity) {
            removeNode(dummytail.prev);
        }
        Node newnode = new Node(k, v);

        insert(newnode);
    }

    int get(int k) {
        if (!map.containsKey(k))
            return -1;

        Node node = map.get(k);
        removeNode(node);
        insert(node);
        return node.v;
    }

    void insert(Node node) {
        map.put(node.k, node);

        node.next = dummyhead.next;
        node.prev = dummyhead;
        dummyhead.next.prev = node;
        dummyhead.next = node;
    }

    void removeNode(Node node) {
        map.remove(node.k);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {

        LRUCaching cache = new LRUCaching(2);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 50);

        System.out.println(cache.get(1));
    }
}