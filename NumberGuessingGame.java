import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final String FILE_NAME = "guessed_numbers.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Generate a random number between 1 and 100
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        // ArrayList to store guessed numbers
        ArrayList<Integer> guessedNumbers = new ArrayList<>();

        // Load previously guessed numbers from the file
        loadGuessedNumbers(guessedNumbers);

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.");

        // Main game loop
        while (true) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            // Add the guessed number to the ArrayList
            guessedNumbers.add(userGuess);

            // Check if the guess is correct
            if (userGuess == randomNumber) {
                System.out.println("Congratulations! You guessed the correct number.");

                // Save guessed numbers to the file
                saveGuessedNumbers(guessedNumbers);
                break; // Exit the loop if the guess is correct
            } else {
                // Provide feedback and continue the loop
                System.out.println("Wrong guess. Try again!");

                // Display guessed numbers
                System.out.println("Your guessed numbers: " + guessedNumbers);

                // Provide a hint
                if (userGuess < randomNumber) {
                    System.out.println("Hint: Try a higher number.");
                } else {
                    System.out.println("Hint: Try a lower number.");
                }
            }
        }

        // Close the scanner
        scanner.close();
    }

    private static void loadGuessedNumbers(ArrayList<Integer> guessedNumbers) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                guessedNumbers.add(Integer.parseInt(line));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not read from the file. Starting with an empty list.");
        }
    }

    private static void saveGuessedNumbers(ArrayList<Integer> guessedNumbers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int number : guessedNumbers) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write to the file.");
        }
    }
}

