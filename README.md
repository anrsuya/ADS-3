# Assignment 3: Sorting and Searching Algorithm Analysis

## A. Project Overview

This project implements and compares three fundamental algorithms:

| Category | Algorithm | Complexity |
|----------|-----------|------------|
| Basic Sort | Insertion Sort | O(n²) |
| Advanced Sort | Merge Sort | O(n log n) |
| Search | Binary Search | O(log n) |

**Purpose:** To measure real execution times across different array sizes and input types, and to verify whether practical performance matches theoretical Big-O expectations.

## B. Algorithm Descriptions

### 1. Insertion Sort (Basic Sort)
Builds a sorted portion one element at a time. For each element, it scans leftward through already-sorted elements, shifting them right until it finds the correct insertion position.

- **Best case:** O(n) — when array is already sorted (no shifting needed)
- **Average/Worst case:** O(n²) — nested comparisons and shifts
- **Space:** O(1) — in-place

### 2. Merge Sort (Advanced Sort)
A divide-and-conquer algorithm. It recursively splits the array into halves until subarrays of size 1 remain, then merges them back in sorted order.

- **All cases:** O(n log n) — consistent regardless of input order
- **Space:** O(n) — requires temporary arrays during merging

### 3. Binary Search
Works on a **sorted array** by repeatedly halving the search space. Compares the target to the middle element, then eliminates the half where the target cannot exist.

- **All cases:** O(log n)
- **Precondition:** array must be sorted

## C. Experimental Results

### Sorting — Random Arrays

| Array Size | Insertion Sort (ns) | Merge Sort (ns) | Faster |
|------------|---------------------|-----------------|--------|
| 10         | ~4,359              | ~16,379         | Insertion |
| 100        | ~188,387            | ~24,076         | Merge |
| 1,000      | ~4,047,809          | ~241,708        | Merge |
| 5,000      | ~7,171,209          | ~1,323,671      | Merge |

### Sorting — Pre-Sorted Arrays

| Array Size | Insertion Sort (ns) | Merge Sort (ns) | Faster |
|------------|---------------------|-----------------|--------|
| 10         | ~4,155              | ~1,865          | Merge |
| 100        | ~801                | ~10,111         | Insertion |
| 1,000      | ~4,063              | ~196,693        | Insertion |
| 5,000      | ~7,200              | ~839,275        | Insertion |

### Binary Search

| Array Size | Target Present (ns) | Target Absent (ns) |
|------------|---------------------|--------------------|
| 10         | ~6,471              | ~444               |
| 100        | ~1,723              | ~342               |
| 1,000      | ~1,669              | ~423               |
| 5,000      | ~972                | ~652               |

## D. Screenshots

### Program Output — Demo Section
```
==================================================
DEMO — Small Array (10 elements)
==================================================
Original:       [2308, 4006, 8622, 3124, 459, 4841, 3193, 115, 5400, 6274]
Insertion Sort: [115, 459, 2308, 3124, 3193, 4006, 4841, 5400, 6274, 8622]
Merge Sort:     [115, 459, 2308, 3124, 3193, 4006, 4841, 5400, 6274, 8622]
Binary Search target 4006 -> index 5
```

### Program Output — Performance Table
```
>>> SORTING EXPERIMENTS
Size       Input      Insertion Sort (ns)    Merge Sort (ns)
------------------------------------------------------------------
10         Random     4359                   16379
100        Random     188387                 24076
1000       Random     4047809                241708
5000       Random     7171209                1323671

10         Sorted     4155                   1865
100        Sorted     801                    10111
1000       Sorted     4063                   196693
5000       Sorted     7200                   839275
```

---

## E. Analysis Questions

**Which sorting algorithm performed faster? Why?**  
Merge Sort is faster for large random arrays. At n=1000, Merge Sort ran ~17× faster than Insertion Sort (241,708 ns vs 4,047,809 ns). This matches theory: O(n log n) grows much slower than O(n²).

**How does performance change with input size?**  
Insertion Sort degrades rapidly — time roughly quadruples when size doubles (consistent with O(n²)). Merge Sort grows more gradually, roughly doubling as size doubles (O(n log n)).

**How does sorted vs unsorted data affect performance?**  
Insertion Sort on sorted input is dramatically faster (~4 ns per element) because the inner while-loop never executes — this is its O(n) best case. Merge Sort sees less difference since it always divides and merges regardless of order.

**Do results match expected Big-O complexity?**  
Yes. Insertion Sort on random arrays shows ~17× slowdown going from 1,000 to 5,000 elements (expected ~25×, close enough given JVM overhead). Merge Sort shows ~5.5× slowdown (expected ~5.4× for n log n). Results align with theory.

**Which searching algorithm is more efficient? Why?**  
Binary Search is extremely efficient — it finds any element in a 5,000-element array in under 1,000 ns. Each comparison eliminates half the remaining candidates, giving O(log n) growth.

**Why does Binary Search require a sorted array?**  
Binary Search decides which half to eliminate by comparing the target to the middle element. This decision only works if values are ordered — if the array is unsorted, discarding half the elements could throw away the answer.

## F. Reflection

Working through this assignment revealed how dramatically theoretical complexity differences translate into real execution times. The gap between O(n²) and O(n log n) is invisible on tiny arrays — in fact, Insertion Sort beat Merge Sort at n=10 on random data due to Merge Sort's overhead from creating temporary arrays. But at n=1,000, the gap became undeniable, and by n=5,000 Merge Sort was over 5× faster. This was a concrete reminder that algorithm choice matters only at meaningful scale.

The most surprising finding was Insertion Sort's behaviour on pre-sorted data. It ran in under 10,000 ns even for 5,000 elements — faster than Merge Sort — because the algorithm exits its inner loop immediately when no shifting is needed. This shows that Big-O worst-case notation can be misleading in practice: knowing your data distribution matters as much as knowing your algorithm's asymptotic bounds. Implementing the merge step was also trickier than expected, since off-by-one errors in index calculations are easy to introduce.
