import java.util.*;

public class FIFO_PageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of frames
        System.out.print("Enter number of frames: ");
        int framesCount = sc.nextInt();

        // Input number of pages
        System.out.print("Enter number of pages: ");
        int pagesCount = sc.nextInt();

        int[] pages = new int[pagesCount];

        // Input reference string
        System.out.println("Enter the reference string:");
        for (int i = 0; i < pagesCount; i++) {
            pages[i] = sc.nextInt();
        }

        // Initialize queue for frames
        Queue<Integer> frames = new LinkedList<>();
        int pageFaults = 0, pageHits = 0;

        System.out.println("\nPage Replacement Process (FIFO):");
        for (int i = 0; i < pagesCount; i++) {
            int currentPage = pages[i];

            if (frames.contains(currentPage)) {
                // Page Hit
                pageHits++;
                System.out.println("Page " + currentPage + " => HIT   | Frames: " + frames);
            } else {
                // Page Fault
                pageFaults++;
                if (frames.size() == framesCount) {
                    int removed = frames.poll(); // remove oldest page
                    System.out.println("Page " + removed + " removed.");
                }
                frames.add(currentPage);
                System.out.println("Page " + currentPage + " => FAULT | Frames: " + frames);
            }
        }

        // Final results
        System.out.println("\nTotal Page Hits   = " + pageHits);
        System.out.println("Total Page Faults = " + pageFaults);

        sc.close();
    }
}
