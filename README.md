# DSA Practice

This repository contains data structures and algorithm practice implementations in Java, with a placeholder for C++ solutions.

## Current Implementations

| Problem / Structure | Language | File | Core idea | Operations |
| --- | --- | --- | --- | --- |
| LRU Cache | Java | `java_impl/LRUCache.java` | Hash map plus doubly linked list | `get`, `put` |
| LFU Cache | Java | `java_impl/LFUCache.java` | Hash maps plus frequency buckets | `get`, `put` |
| Median Finder | Java | `java_impl/MedianFinder.java` | Two heaps: max heap for lower half, min heap for upper half | `addNum`, `findMedian` |

## Repository Structure

```text
.
+-- c++/
|   `-- LRUCache.cpp
`-- java_impl/
    |-- LFUCache.java
    |-- LRUCache.java
    `-- MedianFinder.java
```

## Java Solutions

All Java classes are declared in the `java_impl` package.

Compile the current Java implementations from the repository root:

```bash
javac java_impl/*.java
```

Because this repository currently stores reusable classes rather than executable programs, create a small driver class or unit test in the `java_impl` package when you want to run a solution.

Example:

```java
package java_impl;

public class Main {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        System.out.println(cache.get(1));
    }
}
```

Then compile and run:

```bash
javac java_impl/*.java
java java_impl.Main
```

## Implementation Notes

### LRU Cache

`LRUCache` keeps key lookups in a `HashMap` and tracks recency with a custom doubly linked list. Recently accessed or updated nodes are moved to the front, and the tail node is evicted when capacity is full.

Expected complexity:

- `get`: `O(1)`
- `put`: `O(1)`

### LFU Cache

`LFUCache` stores nodes by key and groups nodes by frequency with `LinkedHashSet` buckets. When the cache is full, it evicts from the current minimum-frequency bucket. `LinkedHashSet` preserves insertion order inside a frequency bucket, so ties are handled by least-recent use within that frequency.

Expected complexity:

- `get`: `O(1)` average
- `put`: `O(1)` average

### Median Finder

`MedianFinder` maintains two priority queues:

- `small`: max heap containing the lower half of values
- `large`: min heap containing the upper half of values

Expected complexity:

- `addNum`: `O(log n)`
- `findMedian`: `O(1)`

## C++ Solutions

The `c++/LRUCache.cpp` file exists, but it is currently empty. Add the C++ implementation there when ready.

## Suggested Next Steps

- Add unit tests or simple driver programs for each implementation.
- Add a C++ implementation for `LRUCache`.
- Consider adding one folder per problem if the repository grows.
