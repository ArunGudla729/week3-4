import java.util.Arrays;

class Trade {
    String id;
    int volume;

    Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() { return id + ":" + volume; }
}

public class problems {

    // --- MERGE SORT (Ascending & Stable) ---
    public static void mergeSort(Trade[] trades) {
        if (trades.length < 2) return;
        int mid = trades.length / 2;
        Trade[] left = Arrays.copyOfRange(trades, 0, mid);
        Trade[] right = Arrays.copyOfRange(trades, mid, trades.length);

        mergeSort(left);
        mergeSort(right);
        merge(trades, left, right);
    }

    private static void merge(Trade[] result, Trade[] left, Trade[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            // Use <= to maintain stability
            if (left[i].volume <= right[j].volume) result[k++] = left[i++];
            else result[k++] = right[j++];
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
    }

    // --- QUICK SORT (Descending & In-place) ---
    public static void quickSort(Trade[] trades, int low, int high) {
        if (low < high) {
            int pIndex = partition(trades, low, high);
            quickSort(trades, low, pIndex - 1);
            quickSort(trades, pIndex + 1, high);
        }
    }

    private static int partition(Trade[] trades, int low, int high) {
        // Pivot selection: using the last element (Lomuto)
        int pivot = trades[high].volume;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Change to > for Descending order
            if (trades[j].volume > pivot) {
                i++;
                swap(trades, i, j);
            }
        }
        swap(trades, i + 1, high);
        return i + 1;
    }

    private static void swap(Trade[] trades, int i, int j) {
        Trade temp = trades[i];
        trades[i] = trades[j];
        trades[j] = temp;
    }

    // --- UTILITIES ---
    public static long computeTotalVolume(Trade[] trades) {
        long total = 0;
        for (Trade t : trades) total += t.volume;
        return total;
    }

    public static void main(String[] args) {
        Trade[] morning = {new Trade("trade3", 500), new Trade("trade1", 100), new Trade("trade2", 300)};
        Trade[] afternoon = {new Trade("trade4", 200), new Trade("trade5", 400)};

        // 1. Merge Sort (Ascending)
        mergeSort(morning);
        System.out.println("MergeSort (Asc): " + Arrays.toString(morning));

        // 2. Quick Sort (Descending)
        quickSort(morning, 0, morning.length - 1);
        System.out.println("QuickSort (Desc): " + Arrays.toString(morning));

        // 3. Total Volume
        long total = computeTotalVolume(morning) + computeTotalVolume(afternoon);
        System.out.println("Total Volume: " + total);
    }
}
