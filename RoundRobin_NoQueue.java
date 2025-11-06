import java.util.*;

public class RoundRobin_NoQueue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        int[] rem = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter Arrival Time of P" + pid[i] + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time of P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
            rem[i] = bt[i];
        }

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        int time = 0, completed = 0;
        boolean executed;

        while (completed != n) {
            executed = false;

            for (int i = 0; i < n; i++) {
           
                if (at[i] <= time && rem[i] > 0) {
                    executed = true; 

                    if (rem[i] > tq) {
                        time += tq;
                        rem[i] -= tq;
                    } else {
                        time += rem[i];
                        rem[i] = 0;
                        ct[i] = time;
                        completed++;
                    }
                }
            }

            if (!executed)
                time++;
        }

        double totalTAT = 0, totalWT = 0;
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            totalTAT += tat[i];
            totalWT += wt[i];
        }

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.printf("\nAverage TAT = %.2f", totalTAT / n);
        System.out.printf("\nAverage WT = %.2f\n", totalWT / n);
       
        

        sc.close();
    }
}
