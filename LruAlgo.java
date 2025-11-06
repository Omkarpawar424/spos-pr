import java.util.*;

public class LruAlgo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read page reference sequence length
        System.out.print("Enter number of page references: ");
        int n = sc.nextInt();
        int[] pages = new int[n];

        System.out.println("Enter the page reference sequence (space separated):");
        for (int i = 0; i < n; i++) pages[i] = sc.nextInt();

        // Read number of frames
        System.out.print("Enter number of page frames: ");
        int frameSize = sc.nextInt();

        // Frames and bookkeeping
        ArrayList<Integer> frames = new ArrayList<>(frameSize);
        // Map page -> last used time (index in sequence)
        HashMap<Integer, Integer> lastUsed = new HashMap<>();

        int pageFaults = 0;

        System.out.println("\nStep\tPage\tFrames");
        for (int time = 0; time < n; time++) {
            int page = pages[time];

            // If page already in frames -> hit, just update last used time
            if (frames.contains(page)) {
                lastUsed.put(page, time);
            } else {
                // Page fault
                pageFaults++;

                if (frames.size() < frameSize) {
                    // Empty frame available: just add
                    frames.add(page);
                    lastUsed.put(page, time);
                } else {
                    // Need to replace LRU page:
                    // find page in frames with smallest lastUsed time
                    int lruPage = frames.get(0);
                    int minTime = lastUsed.getOrDefault(lruPage, Integer.MAX_VALUE);
                    for (int p : frames) {
                        int t = lastUsed.getOrDefault(p, Integer.MAX_VALUE);
                        if (t < minTime) {
                            minTime = t;
                            lruPage = p;
                        }
                    }
                    // Replace lruPage with current page
                    int idx = frames.indexOf(lruPage);
                    frames.set(idx, page);

                    // remove old page's lastUsed entry (optional)
                    lastUsed.remove(lruPage);
                    // set last used for new page
                    lastUsed.put(page, time);
                }
            }

            // Print current state of frames
            System.out.print((time + 1) + "\t" + page + "\t");
            for (int f = 0; f < frames.size(); f++) {
                System.out.print("[" + frames.get(f) + "] ");
            }
            System.out.println();
        }

        System.out.println("\nTotal page faults: " + pageFaults);
        sc.close();
    }
}
