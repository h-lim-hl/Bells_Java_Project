import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final String INVALID_INPUT = "Invalid Input!";

    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);
        ArrayList<Weapon> stock = new ArrayList<>();
        final String[] options = {
                "Print Inventory",
                "Print Weapon Templates",
                "Design New Weapon Template",
                "Edit Design Template",
                "Add Stock",
                "Decrease Stock",
                "Delete Weapon Template",
                "Test Weapon",
                "Exit"
        };

        int userChoice = -1;
        do {
            printMenu(options);
            userChoice = extractInt(inScanner);
            switch (userChoice) {
                case 1: // Print Inventory
                    break;
                case 2: // Print Weapon Template
                    break;
                case 3: // Design New Weapon Template
                    break;
                case 4: // Edit Design Template
                    break;
                case 5: // Add Stock
                    break;
                case 6: // Decrease Stock
                    break;
                case 7: // Delete Weapon Template
                    break;
                case 8: // Test Weapon
                    break;
            }
        } while (userChoice == options.length - 1);
    }

    private static void printMenu(String[] options) {
        System.out.println("==== Welcome to Fantasy Hephaestus's Forge ====");
        System.out.println("Available operations:");
        for (int i = 0; i < options.length; ++i) {
            System.out.printf("(%d) %s\n", i, options[i]);
        }
        System.out.print("Please select an operation: ");
    }

    private static boolean isValidOption(int input, String[] options) {
        return -1 < input && input < options.length;
    }

    private static int extractInt(Scanner sc) {
        int toRet = Integer.MIN_VALUE;
        String input = "";
        try {
            input = sc.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.printf(
                    "extractInt(): nextLine(): %s", e.getMessage()
            );
        }
        try {
            toRet = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT);
        }
        return toRet;
    }

    private static double extractDouble(Scanner sc) {
        double toRet = Double.NaN;
        String input = "";
        try {
            input = sc.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.printf(
                    "extractDouble(): nextLine(): %s", e.getMessage()
            );
        }
        try {
            toRet = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INPUT);
        }
        return toRet;
    }
}
