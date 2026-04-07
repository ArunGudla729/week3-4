import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Banking Transaction for Audit Compliance.
 */
class Transaction {
    String id;
    double fee;
    String timestamp; // Format HH:mm

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

class Problems{

    /**
     * Bubble Sort: Optimized for small batches (<= 100).
     * Uses adjacent swaps and early termination if sorted.
     */
    public static void bubbleSort(List<Transaction> transactions) {
        int n = transactions.size();
        int swaps = 0;
        int passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).fee > transactions.get(j + 1).fee) {
                    // Swap transactions
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                    swapped = true;
                    swaps++;
                }
            }
            // Early termination if no two elements were swapped by inner loop
            if (!swapped) break;
        }
        System.out.println("BubbleSort Result: " + transactions + " // " + passes + " passes, " + swaps + " swaps");
    }

    /**
     * Insertion Sort: Optimized for medium batches (100-1,000).
     * Sorts by Fee first, then Timestamp (Stability maintained).
     */
    public static void insertionSort(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 1; i < n; ++i) {
            Transaction key = transactions.get(i);
            int j = i - 1;

            // Shift elements that are greater than key.fee
            // Or if fees are equal, shift based on timestamp (for audit order)
            while (j >= 0 && (transactions.get(j).fee > key.fee ||
                    (transactions.get(j).fee == key.fee && transactions.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                transactions.set(j + 1, transactions.get(j));
                j = j - 1;
            }
            transactions.set(j + 1, key);
        }
        System.out.println("InsertionSort (Fee+TS) Result: " + transactions);
    }

    /**
     * Flags high-fee outliers (> $50).
     */
    public static void flagOutliers(List<Transaction> transactions) {
        System.out.print("High-fee outliers: ");
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.fee > 50.0) {
                System.out.print("[" + t.id + ":$" + t.fee + "] ");
                found = true;
            }
        }
        if (!found) System.out.print("None");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Transaction> auditBatch = new ArrayList<>();
        auditBatch.add(new Transaction("id1", 10.5, "10:00"));
        auditBatch.add(new Transaction("id2", 25.0, "09:30"));
        auditBatch.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Processing Small Batch Audit...");
        bubbleSort(new ArrayList<>(auditBatch));

        System.out.println("\nProcessing Medium Batch Audit (with Timestamp Priority)...");
        insertionSort(new ArrayList<>(auditBatch));

        System.out.println("\nCompliance Check:");
        flagOutliers(auditBatch);
    }
}

