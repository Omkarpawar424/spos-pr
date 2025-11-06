import java.util.*;

public class Optimal_PageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of frames
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        // Input number of pages
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];

        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        List<Integer> memory = new ArrayList<>();
        int pageFaults = 0;

        // Processing each page
        for (int i = 0; i < n; i++) {
            int currentPage = pages[i];

            // If page already in memory -> no fault
            if (memory.contains(currentPage)) {
                System.out.println("Step " + (i + 1) + " -> " + memory);
                continue;
            }

            // Page fault occurs
            if (memory.size() < frames) {
                memory.add(currentPage);
            } else {
                // Find page to replace using OPTIMAL logic
                int farthestIndex = -1;
                int pageToReplace = -1;

                for (int page : memory) {
                    int nextUse = Integer.MAX_VALUE;

                    // Look ahead to see when this page will be used next
                    for (int j = i + 1; j < n; j++) {
                        if (pages[j] == page) {
                            nextUse = j;
                            break;
                        }
                    }

                    // If this page is never used again, replace it immediately
                    if (nextUse == Integer.MAX_VALUE) {
                        pageToReplace = page;
                        break;
                    }

                    // Otherwise, choose the one with farthest future use
                    if (nextUse > farthestIndex) {
                        farthestIndex = nextUse;
                        pageToReplace = page;
                    }
                }

                memory.remove(Integer.valueOf(pageToReplace));
                memory.add(currentPage);
            }

            pageFaults++;
            System.out.println("Step " + (i + 1) + " -> " + memory);
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}
