import java.io.*;
import java.util.*;

public class macropass1 {
    public static void main(String[] args) throws Exception {

        // Open the input and output files
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter mnt = new PrintWriter("mnt.txt"); // Macro Name Table
        PrintWriter mdt = new PrintWriter("mdt.txt"); // Macro Definition Table
        PrintWriter ala = new PrintWriter("ala.txt"); // Argument List Array

        String line;
        int mdtIndex = 1; // Keeps track of where each macro starts in MDT
        boolean insideMacro = false;

        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, " ,");
            String first = st.nextToken();

            // Detect start of macro definition
            if (first.equalsIgnoreCase("MACRO")) {
                // Read next line = macro header
                line = br.readLine();
                StringTokenizer header = new StringTokenizer(line, " ,");

                String macroName = header.nextToken(); // macro name
                mnt.println(macroName + "\t" + mdtIndex); // store in MNT
                ala.println(macroName); // new section for arguments

                // Store macro arguments in ALA
                while (header.hasMoreTokens()) {
                    String arg = header.nextToken();
                    ala.println(arg);
                }

                // Write macro name in MDT
                mdt.println(macroName);
                insideMacro = true;
                continue;
            }

            // Inside a macro body until MEND is found
            if (insideMacro) {
                if (first.equalsIgnoreCase("MEND")) {
                    mdt.println("MEND");
                    insideMacro = false; // end of macro
                    continue;
                }

                // Replace arguments (&A, &B, etc.) with placeholders (ALA1, ALA2...)
                StringTokenizer tokens = new StringTokenizer(line, " ,");
                while (tokens.hasMoreTokens()) {
                    String tok = tokens.nextToken();
                    if (tok.charAt(0) == '&')
                        mdt.print("ALA" + tok.substring(1) + " ");
                    else
                        mdt.print(tok + " ");
                }
                mdt.println();
            }
        }

        // Close all files
        br.close();
        mnt.close();
        mdt.close();
        ala.close();

        System.out.println("✅ Macro Pass 1 executed successfully!");
    }
}
