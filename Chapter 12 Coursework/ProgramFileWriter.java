import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProgramFileWriter {
    public static void main(String[] args) {
        File file = new File("Exercise12_15.txt");

        try (PrintWriter output = new PrintWriter(file)) {
            for (int i = 0; i < 100; i++) {
                int randomInt = (int) (Math.random() * 1000);
                output.print(randomInt + " ");
            }
            System.out.println("Wrote 100 random integers to " + file.getName());
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not create or open the file for writing.");
            e.printStackTrace();
            return;
        }

        ArrayList<Integer> list = new ArrayList<>();
        
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextInt()) {
                list.add(input.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find the file to read.");
            e.printStackTrace();
            return;
        }

        Collections.sort(list);

        System.out.println("\nData read from file and displayed in increasing order:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
    }
}
