Transaction Log Lookup (Audit & Compliance)
This module provides high-speed lookup tools for Citi transaction forensics. It allows investigators to scan millions of logs to identify specific Account IDs and quantify transaction frequency.
🚀 Key Features
Forensic Linear Search: Scans unsorted logs to find the first instance of a transaction. Best for real-time streams where sorting hasn't occurred.
Logarithmic Binary Search: Utilizes a divide-and-conquer approach on sorted logs to find accounts in



time.
Duplicate Range Detection: Specialized logic to find the first and last occurrence of an ID, allowing for instant calculation of total transactions per account.
Audit Metrics: Tracks comparison counts to evaluate search efficiency across large datasets (1M+ entries).
📊 Complexity Analysis
Method	Best Case	Worst Case	Requirement
Linear Search







None (Works on unsorted)
Binary Search







Data must be Sorted
💻 Usage Example
java
// For 1 million logs:
// Linear might take 1,000,000 comparisons.
// Binary will take ~20 comparisons.

int first = TransactionLookup.binarySearchBoundary(logs, "accB", true);
int last = TransactionLookup.binarySearchBoundary(logs, "accB", false);
int totalTransactions = last - first + 1;
Use code with caution.

⚖️ Business Use Cases
Dispute Resolution: Quickly locating a specific transaction based on a customer's Account ID.
Regulatory Reporting: Counting the frequency of transactions for "Anti-Money Laundering" (AML) flags.
System Audits: Verifying transaction integrity during end-of-day processing.