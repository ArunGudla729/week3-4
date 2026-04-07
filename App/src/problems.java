import java.util.Arrays;

public class problems {

    // --- LINEAR SEARCH (Unsorted - O(n)) ---
    public static int linearSearch(int[] riskBands, int threshold) {
        for (int i = 0; i < riskBands.length; i++) {
            if (riskBands[i] == threshold) return i;
        }
        return -1; // Not found
    }

    // --- BINARY SEARCH: FLOOR (Largest value <= target) ---
    public static int findFloor(int[] riskBands, int target) {
        int low = 0, high = riskBands.length - 1;
        int floor = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (riskBands[mid] == target) return riskBands[mid];

            if (riskBands[mid] < target) {
                floor = riskBands[mid]; // Potential candidate
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    // --- BINARY SEARCH: CEILING (Smallest value >= target) ---
    public static int findCeiling(int[] riskBands, int target) {
        int low = 0, high = riskBands.length - 1;
        int ceiling = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (riskBands[mid] == target) return riskBands[mid];

            if (riskBands[mid] > target) {
                ceiling = riskBands[mid]; // Potential candidate
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceiling;
    }

    public static void main(String[] args) {
        // Pre-sorted risk levels for a client profile
        int[] riskBands = {10, 25, 50, 100};
        int clientRisk = 30;

        System.out.println("Market Risk Bands: " + Arrays.toString(riskBands));
        System.out.println("New Client Risk Score: " + clientRisk);

        // 1. Find Floor (Lower Bound Compliance)
        int floor = findFloor(riskBands, clientRisk);
        System.out.println("Risk Floor (Largest <= 30): " + (floor != -1 ? floor : "None"));

        // 2. Find Ceiling (Upper Bound Pricing)
        int ceiling = findCeiling(riskBands, clientRisk);
        System.out.println("Risk Ceiling (Smallest >= 30): " + (ceiling != -1 ? ceiling : "None"));
    }
}
