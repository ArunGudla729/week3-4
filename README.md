Historical Trade Volume Analysis (Citi Trading Desk)
This project provides a Java-based solution for processing and analyzing high-volume trade data. It focuses on implementing fundamental sorting algorithms to organize market data by volume, ensuring both stability for audit trails and efficiency for large datasets (up to 1 million daily trades).
🚀 Key Features
Stable Sorting: Implements Merge Sort to sort trades by volume (Ascending). Stability ensures that trades with identical volumes maintain their original relative order (e.g., by timestamp).
In-Place Sorting: Implements Quick Sort for descending volume analysis. This approach minimizes memory overhead by sorting within the existing array structure.
Session Merging: Logic to combine multiple trading sessions (e.g., Morning and Afternoon) into a unified report.
Volume Analytics: Utility to calculate the total aggregate volume post-sorting for market trend reporting.
🛠️ Algorithms Used
Algorithm	Order	Time Complexity	Space Complexity	Best Use Case
Merge Sort	Ascending








Audit trails where stability is required.
Quick Sort	Descending




avg



High-speed, memory-constrained environments.
💻 Usage Example
java
// Define Trade data
Trade[] morning = {
new Trade("trade3", 500),
new Trade("trade1", 100),
new Trade("trade2", 300)
};

// Perform Stable Merge Sort (Ascending)
TradeVolumeAnalysis.mergeSort(morning);
// Output: [trade1:100, trade2:300, trade3:500]

// Perform Quick Sort (Descending)
TradeVolumeAnalysis.quickSort(morning, 0, morning.length - 1);
// Output: [trade3:500, trade2:300, trade1:100]
Use code with caution.

📊 Business Use Cases
Market Trend Reports: Generating daily summaries for the Citi trading desk.
Portfolio Rebalancing: Identifying high-volume assets for immediate action.
HFT Analytics: Rapidly ordering execution data for algorithmic feedback loops.