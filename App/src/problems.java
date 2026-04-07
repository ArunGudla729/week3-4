import java.util.Arrays;

class Transaction {
    String accountId;
    String txId;

    Transaction(String accountId, String txId) {
        this.accountId = accountId;
        this.txId = txId;
    }

    @Override
    public String toString() { return accountId + "(" + txId + ")"; }
}

public class problems {

    // --- LINEAR SEARCH (O(n)) ---
    public static int linearSearchFirst(Transaction[] logs, String target) {
        int comparisons = 0;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].accountId.equals(target)) {
                System.out.println("Linear Search Comparisons: " + comparisons);
                return i;
            }
        }
        return -1;
    }

    // --- BINARY SEARCH (O(log n)) ---
    // Finds the boundary index for duplicates
    public static int binarySearchBoundary(Transaction[] logs, String target, boolean findFirst) {
        int low = 0, high = logs.length - 1;
        int result = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int cmp = target.compareTo(logs[mid].accountId);

            if (cmp == 0) {
                result = mid;
                if (findFirst) high = mid - 1; // Keep looking left
                else low = mid + 1;            // Keep looking right
            } else if (cmp < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println("Binary Search (" + (findFirst ? "First" : "Last") + ") Comparisons: " + comparisons);
        return result;
    }

    public static void main(String[] args) {
        // Sample Data (Pre-sorted for Binary Search)
        Transaction[] logs = {
                new Transaction("accA", "TX1"),
                new Transaction("accB", "TX2"),
                new Transaction("accB", "TX3"),
                new Transaction("accC", "TX4")
        };

        String target = "accB";

        // 1. Linear Search
        int firstIdx = linearSearchFirst(logs, target);
        System.out.println("Linear: First occurrence of " + target + " at index " + firstIdx);

        // 2. Binary Search + Occurrence Count
        int start = binarySearchBoundary(logs, target, true);
        int end = binarySearchBoundary(logs, target, false);

        if (start != -1) {
            int count = end - start + 1;
            System.out.println("Binary: Found " + target + " at index " + start + ". Total count: " + count);
        } else {
            System.out.println("Account ID not found.");
        }
    }
}
