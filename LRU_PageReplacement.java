import java.util.*;

public class LRU_PageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frameSize = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int pages[] = new int[n];
        System.out.println("Enter the reference string:");
        for(int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        ArrayList<Integer> frames = new ArrayList<>(frameSize);
        int hits = 0, faults = 0;

        System.out.println("\nPage Replacement Process (LRU):");

        for(int page : pages) {

            // HIT — page already in memory
            if(frames.contains(page)) {
                frames.remove(Integer.valueOf(page)); // remove old position
                frames.add(page);                     // add as most recent
                hits++;
                System.out.println("Page " + page + " => HIT   | Frames: " + frames);
            }
            else {
                // FAULT — page not in memory
                if(frames.size() < frameSize) {
                    frames.add(page);
                } else {
                    frames.remove(0); // remove least recently used page
                    frames.add(page);
                }
                faults++;
                System.out.println("Page " + page + " => FAULT | Frames: " + frames);
            }
        }

        System.out.println("\nTotal Page Hits   = " + hits);
        System.out.println("Total Page Faults = " + faults);
    }
}

