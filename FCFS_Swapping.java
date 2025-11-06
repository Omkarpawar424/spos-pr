 import java.util.*;
 public class FCFS_Swapping {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Step 1: Take number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int pid[] = new int[n];  // Process IDs
        int at[] = new int[n];   // Arrival Times
        int bt[] = new int[n];   // Burst Times
        int ct[] = new int[n];   // Completion Times
        int tat[] = new int[n];  // Turnaround Times
        int wt[] = new int[n];   // Waiting Times
        // Step 2: Input process details
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter arrival time for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter burst time for P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
        }
        // Step 3: Sort (swap) processes by Arrival Time
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {
                    // Swap arrival time
                    int temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;
                    // Swap burst time
                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;
                    // Swap process ID
                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }
        // Step 4: Calculate Completion Time (CT)
        ct[0] = at[0] + bt[0];  // First process
        for (int i = 1; i < n; i++) {
            if (at[i] > ct[i - 1]) {
                // CPU is idle until process arrives
                ct[i] = at[i] + bt[i];
            } else {
                ct[i] = ct[i - 1] + bt[i];
            }
        }
        // Step 5: Calculate Turnaround Time (TAT) and Waiting Time (WT)
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }
        // Step 6: Display results
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] +
                    "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }
    }
 }
 