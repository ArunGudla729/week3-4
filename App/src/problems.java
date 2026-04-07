/**
 * Represents a Banking Client for Risk Management.
 */
class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}

public class problems {

    /**
     * Bubble Sort: Ascending order for low-to-high risk review.
     * Uses .length for standard Java arrays.
     */
    public static void bubbleSortAscending(Client[] clients) {
        int n = clients.length; // Corrected: .length instead of .size
        int swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    // Swap
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.print("Bubble (asc): [");
        for(int i=0; i<n; i++) {
            System.out.print(clients[i] + (i == n - 1 ? "" : ", "));
        }
        System.out.println("] // Swaps: " + swaps);
    }

    /**
     * Insertion Sort: Descending order (High Risk first).
     * Uses .length for standard Java arrays.
     */
    public static void insertionSortDescending(Client[] clients) {
        int n = clients.length; // Corrected: .length instead of .size
        for (int i = 1; i < n; i++) {
            Client key = clients[i];
            int j = i - 1;

            // Descending sort: shift if existing element is smaller than key
            while (j >= 0 && (clients[j].riskScore < key.riskScore ||
                    (clients[j].riskScore == key.riskScore && clients[j].accountBalance < key.accountBalance))) {
                clients[j + 1] = clients[j];
                j = j - 1;
            }
            clients[j + 1] = key;
        }
        System.out.print("Insertion (desc): [");
        for(int i=0; i<n; i++) {
            System.out.print(clients[i] + (i == n - 1 ? "" : ", "));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 5000.0),
                new Client("clientA", 20, 12000.0),
                new Client("clientB", 50, 7500.0)
        };

        // Demo 1: Ascending
        bubbleSortAscending(clients.clone());

        // Demo 2: Descending (Priority)
        insertionSortDescending(clients);

        // Demo 3: Top Risks
        System.out.print("Top 3 risks: ");
        int topCount = Math.min(3, clients.length);
        for(int i=0; i < topCount; i++) {
            System.out.print(clients[i].name + "(" + clients[i].riskScore + ")" + (i == topCount - 1 ? "" : ", "));
        }
        System.out.println();
    }
}
