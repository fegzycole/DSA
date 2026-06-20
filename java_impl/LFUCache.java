package java_impl;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    private static class Node {
        int key, value;
        int freq = 1;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int capacity;
    private int minFreq = 0;
    private final Map<Integer, Node> nodeMap = new HashMap<>();
    private final Map<Integer, LinkedHashSet<Node>> freqMap = new HashMap<>();

    public LFUCache(int cap) {
        capacity = cap;
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)) return -1;

        Node node = nodeMap.get(key);

        updateFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) return;

        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);

            node.value = value;

            updateFrequency(node);

            return;
        }

        if (nodeMap.size() == capacity) {
            evictLFU();
        }

        Node node = new Node(key, value);

        freqMap.computeIfAbsent(node.freq, k -> new LinkedHashSet<>())
                .add(node);

        minFreq = 1;

        nodeMap.put(key, node);
    }

    private void updateFrequency(Node node) {
        int oldFreq = node.freq;

        LinkedHashSet<Node> oldSet = freqMap.get(oldFreq);

        oldSet.remove(node);

        if (oldFreq == minFreq && oldSet.isEmpty()) {
            minFreq++;
        }

        node.freq++;

        freqMap.computeIfAbsent(node.freq, k -> new LinkedHashSet<>())
                .add(node);
    }

    private void evictLFU() {
        LinkedHashSet<Node> minFreqSet = freqMap.get(minFreq);

        Node nodeToRemove = minFreqSet.iterator().next();

        minFreqSet.remove(nodeToRemove);

        nodeMap.remove(nodeToRemove.key);
    }
}