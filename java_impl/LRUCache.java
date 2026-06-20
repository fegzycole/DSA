package java_impl;
import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private static class Node {
        public int key, value;
        public Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail;

    public LRUCache(int cap) {
        capacity = cap;

        map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);

        remove(node);
        insertAtFront(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;

        if (map.containsKey(key)) {
            Node node = map.get(key);

            node.value = value;

            remove(node);
            insertAtFront(node);

            return;
        }

        if (map.size() == capacity) {
            Node lru = tail.prev;
            remove(lru);

            map.remove(lru.key);
        }


        Node newNode = new Node(key, value);

        insertAtFront(newNode);

        map.put(key, newNode);
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(Node node) {
        Node prevFirstNode = head.next;

        node.next = prevFirstNode;
        prevFirstNode.prev = node;
        node.prev = head;
        head.next = node;
    }
}