# week3-4
# Problem 1: Transaction Fee Sorting for Audit Compliance

## 📌 Scenario
A banking application processes massive volumes of daily transactions. For compliance reviews, auditors require these transactions to be sorted by fee amount. This project implements a dual-sorting strategy based on batch size to ensure efficiency and stability.

## 🛠️ Sorting Strategy

### 1. Bubble Sort (Small Batches ≤ 100)
*   **Mechanism:** Uses adjacent swaps and a `swapped` flag for **early termination**.
*   **Audit Benefit:** Simple to implement for quick daily small-batch reports.
*   **Complexity:** $O(n^2)$ worst case, but $O(n)$ if the batch is already sorted.

### 2. Insertion Sort (Medium Batches 100–1,000)
*   **Mechanism:** Builds a sorted subarray by shifting larger elements to the right.
*   **Audit Benefit:** Maintains **Stability**. If two transactions have the same fee, their original relative order (timestamp) is preserved, which is critical for legal audit trails.

### 3. Outlier Detection
*   Automatically flags any transaction with a fee exceeding **$50.00** for secondary fraud review.

## 🏗️ Compliance Concepts
*   **Stability:** Essential for financial records where the original entry order must be respected during secondary sorts.
*   **Time Complexity Analysis:**
    *   **Bubble:** Best for nearly sorted small data.
    *   **Insertion:** Highly efficient for small-to-medium datasets and outperforms Bubble Sort in most real-world scenarios.

## 📊 Sample Output
```text
BubbleSort Result: [id3:5.0@10:15, id1:10.5@10:00, id2:25.0@09:30] // 3 passes, 2 swaps
InsertionSort (Fee+TS) Result: [id3:5.0@10:15, id1:10.5@10:00, id2:25.0@09:30]
High-fee outliers: None
