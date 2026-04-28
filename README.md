This project implements and compares three fundamental algorithms:

| Category | Algorithm | Complexity |
|----------|-----------|------------|
| Basic Sort | Insertion Sort | O(n²) |
| Advanced Sort | Merge Sort | O(n log n) |
| Search | Binary Search | O(log n) |

**Purpose:** To measure real execution times across different array sizes and input types, and to verify whether practical performance matches theoretical Big-O expectations.

## Algorithm Descriptions

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
## Screenshots

### Program Output — Demo Section
```
DEMO — Small Array (10 elements)
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

## Reflection

Working through this assignment revealed how dramatically theoretical complexity differences translate into real execution times. The gap between O(n²) and O(n log n) is invisible on tiny arrays — in fact, Insertion Sort beat Merge Sort at n=10 on random data due to Merge Sort's overhead from creating temporary arrays. But at n=1,000, the gap became undeniable, and by n=5,000 Merge Sort was over 5× faster. This was a concrete reminder that algorithm choice matters only at meaningful scale.

The most surprising finding was Insertion Sort's behaviour on pre-sorted data. It ran in under 10,000 ns even for 5,000 elements — faster than Merge Sort — because the algorithm exits its inner loop immediately when no shifting is needed. This shows that Big-O worst-case notation can be misleading in practice: knowing your data distribution matters as much as knowing your algorithm's asymptotic bounds. Implementing the merge step was also trickier than expected, since off-by-one errors in index calculations are easy to introduce.

---

## Project Structure

```
assignment3-sorting-searching/
├── src/
│   ├── Sorter.java
│   ├── Searcher.java
│   ├── Experiment.java
│   └── Main.java
├── docs/
│   └── screenshots/
├── README.md
└── .gitignore
```
