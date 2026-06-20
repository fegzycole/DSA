package java_impl;
import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    private final PriorityQueue<Integer> small;
    private final PriorityQueue<Integer> large;
    private boolean even;

    public MedianFinder() {
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
        even = true;
    }

    public void addNum(int num) {
        if (even) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }

        even = !even;
    }

    public double findMedian() {
        if (even) return (small.poll() + large.poll()) / 2.0;

        return small.peek();
    }
}
