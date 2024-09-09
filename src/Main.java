import java.util.*;

public class Main {
    private static final String QUIT_CHAR = "q";
    private static final String INVALID_INPUT_MSG = String.format(
            "Invalid input try again!\nEnter '%s' to abort", QUIT_CHAR
    );

    private static final Random RNG = new Random(System.currentTimeMillis());
    private static int randBladeCount = 0;
    private static int randHiltCount = 0;
    private static int randMatCount = 0;
    private static int randWeaponCount = 0;

    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);
        HashMap<Weapon, Integer> stock = new HashMap<>();
        final String[] options = {
                /* 1 */ "Print Inventory",
                /* 2 */ "Print Weapon Templates",
                /* 3 */ "Design New Weapon Template",
                /* 4 */ "Edit Design Template",
                /* 5 */ "Add Stock",
                /* 6 */ "Decrease Stock",
                /* 7 */ "Delete Weapon Template",
                /* 8 */ "Test Weapon",
               /* n+1 */ "Exit"
        };

        int userChoice = -1;
        do {
            printMenu(options);
            try {
                userChoice = getInt(inScanner, "Please Enter an Operation");
            } catch (OperationCancelException e) {
                break;
            }
            switch (userChoice) {
                case 1: // Print Inventory
                    printInventory(stock);
                    break;
                case 2: // Print Weapon Templates
                    printWeaponTemplates(stock);
                    break;
                case 3: // Design New Weapon Template
                    try {
                        designNewWeapon(inScanner, stock);
                    } catch (OperationCancelException e) {
                        System.out.println("Received Op Cancel from Designing"
                        + " Weapon Template");
                    }
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
        System.out.println("Application Exiting... Goodbye!");
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

    private static void printInventory
            (final HashMap<Weapon, Integer> inventory) {
        System.out.println("==== Printing Inventory ===");
        inventory.forEach((key, value) -> {
            System.out.println("-------   -------");
            System.out.println(key.toString());
            System.out.printf("Stock left: %d\n", value);
        });
        System.out.println("-------   -------");
        System.out.println("==== Print Complete ====");
    }

    private static void printWeaponTemplates(
            final HashMap<Weapon, Integer> inventory) {
        System.out.println("==== Print Weapon Templates ====");
        int count = 0;
        for(var template : inventory.keySet()) {
            System.out.printf("------- (%d) -------\n", count++);
            System.out.println(template.toString());
        }
        System.out.println("-------  --  -------");
        System.out.println("==== Print Complete ====");
    }

    private static void designNewWeapon(Scanner sc,
                                        HashMap<Weapon, Integer> inv)
        throws OperationCancelException {
        String weaponName,
                hiltName, hiltMatName,
                bladeName, bladeMatName;
        double hiltMatDensity, hiltLength,
                bladeMatDensity, bladeLength, bladeSharpness;
        int weaponRarity;
        System.out.println("==== Creating New Weapon Template ====");
        try {
            bladeMatDensity = getDouble(sc,
                    "Please Enter the Density of the Blade");
            bladeMatName = getString(sc,
                    "Please Name the Blade's Material");
            bladeLength = getDouble(sc,
                    "Please Enter the length of the Blade");
            bladeSharpness = getDouble(sc,
                    "Please Rate the Blade's Sharpness");
            bladeName = getString(sc,
                    "Please Name the Blade");

            hiltMatDensity = getDouble(sc,
                    "Please Enter the Density of the Hilt");
            hiltMatName = getString(sc,
                    "Please Name the Hilt's Material");
            hiltLength = getDouble(sc,
                    "Please Enter the Length of the Hilt");
            hiltName = getString(sc, "Please Name the Hilt");

            weaponRarity = getInt(sc, "Rate the rarity of the Weapon");
            weaponName = getString(sc, "Please Name the Weapon");
        } catch (OperationCancelException e) {
            System.out.println("New Weapon Template Creation Aborted!");
            throw new OperationCancelException();
        }
        inv.put(new Weapon(
                weaponName, weaponRarity,
                hiltName, hiltMatName,
                hiltLength, hiltMatDensity,
                bladeName, bladeMatName, bladeLength,
                bladeSharpness, bladeMatDensity
        ), 0);
        System.out.println("Operation complete!");
        printWeaponTemplates(inv);

    }

    private static String getString(Scanner sc, String prompt)
        throws OperationCancelException {
        String input = "";
        while(true) {
            System.out.print(prompt + ": ");
            input = sc.nextLine();
            if(input.equalsIgnoreCase("q"))
                throw new OperationCancelException();
            else if(input.isBlank() || input.isEmpty())
                System.out.println(INVALID_INPUT_MSG);
            else
                return input;
        }
    }

    private static int getInt(Scanner sc, String prompt)
        throws OperationCancelException {
        String input = "";
        while(true) {
            System.out.print(prompt + ": ");
            input = sc.nextLine();
            if(input.equalsIgnoreCase("q"))
                throw new OperationCancelException();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MSG);
            }
        }
    }

    private static double getDouble(Scanner sc, String prompt)
        throws OperationCancelException {
        String input = "";
        while(true) {
            System.out.print(prompt + ": ");
            input = sc.nextLine();
            if(input.equalsIgnoreCase("q"))
                throw new OperationCancelException();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MSG);
            }
        }
    }

    private static void addRandomWeapon(HashMap<Weapon, Integer> inv) {
        final int MAX_INIT_STOCK = 10;
        final int MAX_RARITY= 3;
        inv.put(new Weapon(
                String.format("RandomWeapon_%d", randWeaponCount++),
                RNG.nextInt(3) + 1, getRandomHilt(getRandomMaterial()),
                getRandomBlade(getRandomMaterial())
                ), RNG.nextInt(MAX_INIT_STOCK)+1);
    }

    private static Material getRandomMaterial() {
        final Material[] PREMADE_MAT = {
                new Material("Wood", 600),
                new Material("Iron", 800),
                new Material("Steel", 1000)
        };
        return PREMADE_MAT[RNG.nextInt(PREMADE_MAT.length)];
    }

    private static Blade getRandomBlade(Material mat) {
        final double MAX_LENGTH = 4.0;
        final double MAX_SHARPNESS = 5.0;
        return new Blade(String.format("RandomBlade_%d", randBladeCount++),
                mat, RNG.nextDouble() * MAX_LENGTH,
                RNG.nextDouble() * MAX_SHARPNESS);
    }

    private static Hilt getRandomHilt(Material mat) {
        final double MAX_LENGTH = 1.0;
        return new Hilt(String.format("RandomHilt_%d", randHiltCount++),
                mat, RNG.nextDouble() * MAX_LENGTH);
    }
}
