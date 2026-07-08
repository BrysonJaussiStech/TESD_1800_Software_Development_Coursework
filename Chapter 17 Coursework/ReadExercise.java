import java.io.*;

public class ReadExercise {
    public static void main(String[] args) {
        // 1. Specify the file name or absolute path
        String fileName = "Exercise17_14.dat"; 

        // 2. Open the file using FileInputStream inside a try-with-resources block
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName))) {
            int value;
            // 3. Read byte by byte until the end of the file (-1)
            while ((value = input.read()) != -1) {
                System.out.print(value + " ");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: The file '" + fileName + "' could not be found.");
            System.out.println("Make sure it is placed in the correct project folder.");
        } catch (IOException ex) {
            System.out.println("An I/O error occurred while reading the file.");
        }
    }
}