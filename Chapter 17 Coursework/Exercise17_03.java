import java.io.*;
import java.util.Random;

public class Exercise17_03 {
    private static final String FILE_NAME = "Exercise17_03.dat";

    public static void main(String[] args) {
        appendRandomIntegers(FILE_NAME);

        int sum = sumIntegersInFile(FILE_NAME);
        System.out.println("The sum of all integers in the file is: " + sum);
    }

    public static void appendRandomIntegers(String fileName) {
        File file = new File(fileName);
        
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file, true))) {
            Random random = new Random();
            
            for (int i = 0; i < 100; i++) {
                int randomInt = random.nextInt(100);
                output.writeInt(randomInt);
            }
            System.out.println("Successfully appended 100 random integers to " + fileName);
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int sumIntegersInFile(String fileName) {
        int sum = 0;
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return 0;
        }

        try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            while (true) {
                sum += input.readInt();
            }
        } catch (EOFException e) {
            System.out.println("Reached the end of the binary file safely.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }

        return sum;
    }
}