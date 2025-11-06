import java.util.Scanner;

public class PriorityNonPreemptiveEasy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] bt = new int[n];   // Burst times
        int[] pr = new int[n];   // Priorities
        int[] wt = new int[n];   // Waiting times
        int[] tat = new int[n];  // Turnaround times
        int[] pid = new int[n];  // Process IDs

        // Input burst times and priorities
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            System.out.print("Enter priority for Process " + (i + 1) + ": ");
            pr[i] = sc.nextInt();
            pid[i] = i + 1; // process IDs (P1, P2, ...)
        }

        // Sort processes by priority (lower number = higher priority)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pr[i] > pr[j]) {
                    // swap priority
                    int temp = pr[i];
                    pr[i] = pr[j];
                    pr[j] = temp;

                    // swap burst time
                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    // swap process id
                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }

        // Calculate waiting and turnaround times
        wt[0] = 0;
        tat[0] = bt[0];
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
            tat[i] = wt[i] + bt[i];
        }

        // Print results
        System.out.println("\nProcess\tBT\tPriority\tWT\tTAT");
        int totalWT = 0, totalTAT = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + bt[i] + "\t" + pr[i] + "\t\t" + wt[i] + "\t" + tat[i]);
            totalWT += wt[i];
            totalTAT += tat[i];
        }

        // Print averages
        System.out.println("\nAverage Waiting Time = " + (float) totalWT / n);
        System.out.println("Average Turnaround Time = " + (float) totalTAT / n);

        sc.close();
    }
}
