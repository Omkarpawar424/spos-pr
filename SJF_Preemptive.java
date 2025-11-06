import java.util.*;

class Process {
    int id, at, bt, ct, tat, wt, rt;
    int remainingBt; // remaining burst time
    boolean isCompleted = false;
}

public class SJF_Preemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        Process[] p = new Process[n];

        // Input arrival and burst times
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].id = i + 1;
            System.out.print("Enter Arrival time of P" + (i + 1) + ": ");
            p[i].at = sc.nextInt();
            System.out.print("Enter Burst time of P" + (i + 1) + ": ");
            p[i].bt = sc.nextInt();
            p[i].remainingBt = p[i].bt;
        }

        int time = 0, completed = 0;
        float sumTAT = 0, sumWT = 0, sumRT = 0;

        // Loop until all processes complete
        while (completed != n) {
            int idx = -1;
            int minBT = Integer.MAX_VALUE;

            // Find process with shortest remaining burst time among arrived ones
            for (int i = 0; i < n; i++) {
                if (p[i].at <= time && !p[i].isCompleted && p[i].remainingBt < minBT) {
                    minBT = p[i].remainingBt;
                    idx = i;
                }
            }

            if (idx != -1) {
                // First response time
                if (p[idx].remainingBt == p[idx].bt) {
                    p[idx].rt = time - p[idx].at;
                }

                // Execute process for 1 unit
                p[idx].remainingBt--;
                time++;

                // If process finished
                if (p[idx].remainingBt == 0) {
                    p[idx].ct = time;
                    p[idx].tat = p[idx].ct - p[idx].at;
                    p[idx].wt = p[idx].tat - p[idx].bt;

                    sumTAT += p[idx].tat;
                    sumWT += p[idx].wt;
                    sumRT += p[idx].rt;

                    p[idx].isCompleted = true;
                    completed++;
                }
            } else {
                // If no process has arrived yet
                time++;
            }
        }

        // Print results
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT\tRT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + p[i].id + "\t" +
                    p[i].at + "\t" + p[i].bt + "\t" + p[i].ct + "\t" +
                    p[i].tat + "\t" + p[i].wt + "\t" + p[i].rt);
        }

        // Print averages
        System.out.println("\nAverage Turnaround Time: " + (sumTAT / n));
        System.out.println("Average Waiting Time: " + (sumWT / n));
        System.out.println("Average Response Time: " + (sumRT / n));
    }
}
