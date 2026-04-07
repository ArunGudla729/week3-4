# Problem 2: Client Risk Score Ranking

## 📌 Scenario
The Risk Management team requires a rapid sorting mechanism for 500 client risk scores. High-risk clients must be identified immediately for prioritized review, while low-risk clients are sorted for standard compliance checks.

## 🛠️ Implementation Strategy

### 1. Low-to-High Risk (Bubble Sort)
*   **Goal:** Ascending order for baseline risk reporting.
*   **Visual Analysis:** Tracks every swap to demonstrate algorithm efficiency during team demos.
*   **Space Complexity:** $O(1)$ — an in-place sort that requires no additional memory.

### 2. Priority Ranking (Insertion Sort)
*   **Goal:** Descending order (Highest Risk first).
*   **Adaptive Logic:** Specifically chosen for its efficiency on nearly-sorted data, common in risk score updates.
*   **Secondary Tie-breaker:** If two clients share the same risk score, the one with the higher **Account Balance** is ranked higher to mitigate larger financial exposure.

## 🏗️ Core Concepts
*   **Space Complexity $O(1)$:** Critical for memory-efficient processing of client lists on internal bank servers.
*   **Stability:** Insertion sort maintains the relative order of equal risk scores, ensuring the account balance secondary sort is accurate.

## 📊 Sample Output
```text
Bubble (asc): [A:20, B:50, C:80] // Swaps: 2
Insertion (desc): [C:80, B:50, A:20]
Top 3 risks: C(80), B(50), A(20)
