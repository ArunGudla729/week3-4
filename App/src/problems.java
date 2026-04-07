import java.util.Arrays;
import java.util.Random;

class Asset {
    String ticker;
    double returnRate;
    double volatility;

    Asset(String ticker, double returnRate, double volatility) {
        this.ticker = ticker;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return String.format("[%s: R:%.1f%%, V:%.1f%%]", ticker, returnRate, volatility);
    }
}

public class problems {

    // --- MERGE SORT (Stable - Ascending Return Rate) ---
    public static void mergeSort(Asset[] assets) {
        if (assets.length < 2) return;
        int mid = assets.length / 2;
        Asset[] left = Arrays.copyOfRange(assets, 0, mid);
        Asset[] right = Arrays.copyOfRange(assets, mid, assets.length);

        mergeSort(left);
        mergeSort(right);
        merge(assets, left, right);
    }

    private static void merge(Asset[] result, Asset[] left, Asset[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            // Stability check: <= ensures original order for ties
            if (left[i].returnRate <= right[j].returnRate) result[k++] = left[i++];
            else result[k++] = right[j++];
        }
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
    }

    // --- HYBRID QUICK SORT (Return DESC, Volatility ASC) ---
    private static final int INSERTION_THRESHOLD = 10;

    public static void quickSort(Asset[] assets, int low, int high) {
        // Hybrid: Use Insertion Sort for small partitions to reduce recursion overhead
        if (low + INSERTION_THRESHOLD > high) {
            insertionSort(assets, low, high);
            return;
        }

        if (low < high) {
            int pIndex = partition(assets, low, high);
            quickSort(assets, low, pIndex - 1);
            quickSort(assets, pIndex + 1, high);
        }
    }

    private static int partition(Asset[] assets, int low, int high) {
        // Pivot Selection: Median-of-Three (Low, Mid, High)
        int mid = low + (high - low) / 2;
        medianOfThree(assets, low, mid, high);
        Asset pivot = assets[high];

        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Multi-criteria: Primary = Return DESC, Secondary = Volatility ASC
            if (assets[j].returnRate > pivot.returnRate ||
                    (assets[j].returnRate == pivot.returnRate && assets[j].volatility < pivot.volatility)) {
                i++;
                swap(assets, i, j);
            }
        }
        swap(assets, i + 1, high);
        return i + 1;
    }

    private static void medianOfThree(Asset[] assets, int low, int mid, int high) {
        if (assets[low].returnRate < assets[mid].returnRate) swap(assets, low, mid);
        if (assets[low].returnRate < assets[high].returnRate) swap(assets, low, high);
        if (assets[mid].returnRate < assets[high].returnRate) swap(assets, mid, high);
        // Move median to high to act as pivot
        swap(assets, mid, high);
    }

    private static void insertionSort(Asset[] assets, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = assets[i];
            int j = i - 1;
            while (j >= low && assets[j].returnRate < key.returnRate) {
                assets[j + 1] = assets[j];
                j--;
            }
            assets[j + 1] = key;
        }
    }

    private static void swap(Asset[] assets, int i, int j) {
        Asset temp = assets[i];
        assets[i] = assets[j];
        assets[j] = temp;
    }

    public static void main(String[] args) {
        Asset[] portfolio = {
                new Asset("AAPL", 12.0, 1.5),
                new Asset("TSLA", 8.0, 2.8),
                new Asset("GOOG", 15.0, 1.2),
                new Asset("MSFT", 12.0, 1.1) // Same return as AAPL, lower volatility
        };

        System.out.println("Original: " + Arrays.toString(portfolio));

        // 1. Merge Sort (Stable Ascending)
        mergeSort(portfolio);
        System.out.println("Merge (Stable Asc): " + Arrays.toString(portfolio));

        // 2. Quick Sort (Hybrid Descending + Volatility ASC)
        quickSort(portfolio, 0, portfolio.length - 1);
        System.out.println("Quick (Desc/Vol): " + Arrays.toString(portfolio));
    }
}
