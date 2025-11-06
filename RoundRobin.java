 import java.util.*;
 class Process {
 int id, arrivalTime, burstTime, remainingTime, completionTime, waitingTime, 
turnaroundTime;
 Process(int id, int arrivalTime, int burstTime) {
 this.id = id;
 this.arrivalTime = arrivalTime;
 this.burstTime = burstTime;
 this.remainingTime = burstTime;
 }
 }
 public class RoundRobin {
 public static void main(String[] args) {
 Scanner sc = new Scanner(System.in);
 // Input: number of processes
 System.out.print("Enter number of processes: ");
 int n = sc.nextInt();
 // Input: time quantum
 System.out.print("Enter time quantum: ");
 int tq = sc.nextInt();
 Process[] processes = new Process[n];
 for (int i = 0; i < n; i++) {
 System.out.print("Enter arrival time of P" + (i + 1) + ": ");
 int at = sc.nextInt();
 System.out.print("Enter burst time of P" + (i + 1) + ": ");
 int bt = sc.nextInt();
 processes[i] = new Process(i + 1, at, bt);
 }
// Ready queue
 Queue<Process> queue = new LinkedList<>();
 int time = 0, completed = 0;
 boolean[] visited = new boolean[n];
 // Sort by arrival time
 Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));
 queue.add(processes[0]);
 visited[0] = true;
 time = processes[0].arrivalTime;
 while (!queue.isEmpty()) {
 Process current = queue.poll();
 // Execute current process
 if (current.remainingTime > tq) {
 time += tq;
 current.remainingTime -= tq;
 } else {
 time += current.remainingTime;
 current.remainingTime = 0;
 current.completionTime = time;
 current.turnaroundTime = current.completionTime - current.arrivalTime;
 current.waitingTime = current.turnaroundTime - current.burstTime;
 completed++;
 }
 // Add newly arrived processes
 for (int i = 0; i < n; i++) {
 if (!visited[i] && processes[i].arrivalTime <= time) {
 queue.add(processes[i]);
 visited[i] = true;
 }
 }
 // Re-add current process if not finished
 if (current.remainingTime > 0) {
 queue.add(current);
 }
 }
 // Print table header
        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT");
        for (Process p : processes) {
            System.out.println("P" + p.id + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" +
                    p.completionTime + "\t" + p.turnaroundTime + "\t" + p.waitingTime);
        }

        // Calculate Average Turnaround Time and Waiting Time
        double avgTAT = Arrays.stream(processes).mapToDouble(p -> p.turnaroundTime).average().orElse(0.0);
        double avgWT = Arrays.stream(processes).mapToDouble(p -> p.waitingTime).average().orElse(0.0);

        System.out.println("\nAverage Turnaround Time: " + avgTAT);
        System.out.println("Average Waiting Time: " + avgWT);
    }
}