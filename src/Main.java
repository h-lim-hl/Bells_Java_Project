import java.util.*;

/**
 * Wrapper class for HashMap&#60;BladedWeapon, Integer&#62;
 */
final class Inventory extends HashMap<BladedWeapon, Integer> {}

/**
 * Main class created to simulate a Weapon's shop.
 */
public class Main {
    /**
     * Quit string code for user abort operation command
     */
    private static final String QUIT_STRING = "q";
    private static final String INVALID_INPUT_MSG = String.format(
            "Invalid input try again!\nEnter '%s' to abort", QUIT_STRING
    );

    // For pretty print
    private static final int OPERATION_PADDING = 2;

    // For use in Random Weapon Generation
    private static final Random RNG = new Random(System.currentTimeMillis());
    private static int randBladeCount = 0;
    private static int randHiltCount = 0;
    private static int randWeaponCount = 0;

    /**
     * Main Default Constructor because intellij JavaDocs thinks it is a good
     * idea.
     */
    public Main () {}
    /**
     * Driver method to simulate a CRUD application.
     * @param args String[] that contains all command line args given
     *             when running this application.
     */
    public static void main(String[] args) {
        Scanner inScanner = new Scanner(System.in);
        Inventory stock = new Inventory();
        final String[] options = {
                /* 1 */ "Print Inventory",
                /* 2 */ "Print Templates",
                /* 3 */ "Design New Template",
                /* 4 */ "Edit Design Template",
                /* 5 */ "Modify Stock",
                /* 6 */ "Remove Template",
                /* 7 */ "Test Template(s)",
                /* 8 */ "Add Random Weapon",
               /* n+1 */ "Exit"
        };

        String padding = "";
        for(int i = 0; i < OPERATION_PADDING; ++i)
            padding = padding.concat("\n");

        int userChoice;
        do {
            userChoice = -1;
            printMenu(options);
            try {
                userChoice = getInt(inScanner,
                        "Please Enter an Operation");
            } catch (OperationCancelException e) {
                break;
            }
            switch (userChoice) {
                case 0: // Print Inventory
                    printInventory(stock);
                    break;
                case 1: // Print Weapon Templates
                    printTemplates(stock);
                    break;
                case 2: // Design New Weapon Template
                    designNewTemplate(inScanner, stock);
                    break;
                case 3: // Edit Design Template
                    editTemplate(inScanner, stock);
                    break;
                case 4: // Modify Stock
                    modifyStock(inScanner, stock);
                    break;
                case 5: // Delete Weapon Template
                    deleteTemplate(inScanner, stock);
                    break;
                case 6: // Test Weapon
                    testStock(inScanner, stock);
                    break;
                case 7: // Add a Random Weapon
                    addRandomWeapon(stock);
                    break;
                case 8:
                    break;
                default:
                    System.out.println(INVALID_INPUT_MSG);
            }
            System.out.print(padding);
        } while (!(userChoice == options.length-1));
        System.out.println("Application Exiting... Goodbye!");
    }

    /**
     * Prints out main menu of the application
     * @param options String[] of all available options.
     */
    private static void printMenu(final String[] options) {
        System.out.println("==== Welcome to Fantasy Hephaestus's Forge ====");
        System.out.println("Available operations:");
        for (int i = 0; i < options.length; ++i) {
            System.out.printf("(%d) %s\n", i, options[i]);
        }
    }

    /**
     * prints all known templates with stock information
     * @param inventory Inventory object that represents the shop inventory.
     */
    private static void printInventory (final Inventory inventory) {
        System.out.println("==== Printing Inventory ===");
        inventory.forEach((key, value) -> {
            System.out.println("-------   -------");
            System.out.println(key.getName());
            System.out.printf("Stock left: %d\n", value);
        });
        System.out.println("-------   -------");
        System.out.println("==== Print Complete ====");
    }

    /**
     * prints all know templates and their details
     * @param inventory Inventory object that represent that shop inventory.
     */
    private static void printTemplates(final Inventory inventory) {
        System.out.println("==== Print Weapon Templates ====");
        int count = 0;
        for(var template : inventory.keySet()) {
            System.out.printf("------- (%d) -------\n", count++);
            System.out.println(template.toString());
        }
        System.out.println("-------  --  -------");
        System.out.println("==== Print Complete ====");
    }

    /**
     * Logic for creating a new Bladed Weapon Template
     * @param sc Scanner that is able to scan user's input.
     * @param inv Inventory object that represents tha shop inventory.
     */
    private static void designNewTemplate(Scanner sc, Inventory inv) {
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
            return;
        }
        inv.put(new BladedWeapon(
                weaponName, weaponRarity,
                hiltName, hiltMatName,
                hiltLength, hiltMatDensity,
                bladeName, bladeMatName, bladeLength,
                bladeSharpness, bladeMatDensity
        ), 0);
        System.out.println("Operation complete!");
        printTemplates(inv);

    }

    /**
     * Logic for editing an existing Bladed Weapon Template
     * @param sc Scanner that can scan the user input.
     * @param inv Inventory object that represents the shop inventory.
     */
    private static void editTemplate(Scanner sc, Inventory inv) {
        final String ABOUT_STRING = "Editing Weapon Template Aborted!";
        String weaponName;

        System.out.println("==== Editing Weapon Template ====");
        try {
            weaponName = getString(sc, "Enter the name of the template to edit");
        } catch (OperationCancelException e) {
            System.out.println(ABOUT_STRING);
            return;
        }

        BladedWeapon tgtBladedWeapon = null;
        try {
            tgtBladedWeapon = getWeapon(weaponName, inv);
        } catch (NoSuchElementException e) {
            System.out.printf("Weapon Template \"%s\" could not be found!",
                    weaponName);
            return;
        }

        // Reusing weaponName
        String bladeName, bladeMatName, hiltName, hiltMatName;
        double bladeSharpness, bladeLength, bladeMatDensity, hiltLength,
                hiltMatDensity;
        int weaponRarity;
        try {
            hiltMatDensity = getDouble(sc,
                "Enter new Hilt Density (negative for unchanged)");
            hiltMatName = getString(sc,
                    "Enter new Hilt Material Name (empty for unchanged)",
                    true);
            hiltLength = getDouble(sc,
                    "Enter new Hilt Length (negative for unchanged)");
            hiltName = getString(sc,
                    "Enter new Hilt Name (empty for unchanged)",
                    true);

            bladeMatDensity = getDouble(sc,
                    "Enter new Blade Density (negative for unchanged)");
            bladeMatName = getString(sc,
                    "Enter new Blade Material Name (empty for unchanged)",
                    true);
            bladeLength = getDouble(sc,
                    "Enter new Blade Length (negative for unchanged)");
            bladeSharpness = getDouble(sc,
                    "Enter new Blade Sharpness (negative for unchanged)");
            bladeName = getString(sc,
                    "Enter new Blade Name (empty for unchanged)",
                    true);

            weaponRarity = getInt(sc,
                    "Enter new Weapon Rarity (negative for unchanged)");
            weaponName = getString(sc,
                    "Enter new Weapon Name (empty for unchanged)",
                    true);

        } catch (OperationCancelException e) {
            System.out.println(ABOUT_STRING);
            return;
        }

        tgtBladedWeapon.setHiltDensity(hiltMatDensity < 0.0 ?
                tgtBladedWeapon.getHiltDensity() : hiltMatDensity);
        tgtBladedWeapon.setHiltMaterialName(
                hiltMatName.isEmpty() || hiltMatName.isBlank() ?
                tgtBladedWeapon.getHiltMaterialName() : hiltMatName);
        tgtBladedWeapon.setHiltLength(hiltLength < 0.0 ?
                tgtBladedWeapon.getHiltLength() : hiltLength);
        tgtBladedWeapon.setHiltName(hiltName.isEmpty() ?
                tgtBladedWeapon.getHiltName() : hiltName);

        tgtBladedWeapon.setBladeDensity(bladeMatDensity < 0.0 ?
                tgtBladedWeapon.getBladeDensity() : bladeMatDensity);
        tgtBladedWeapon.setBladeLength(bladeLength < 0.0 ?
                tgtBladedWeapon.getBladeLength() : bladeLength);
        tgtBladedWeapon.setBladeMaterialName(
                bladeMatName.isEmpty() || bladeMatName.isBlank() ?
                tgtBladedWeapon.getBladeMaterialName() : bladeMatName);
        tgtBladedWeapon.setBladeName(bladeName.isEmpty() || bladeMatName.isBlank() ?
                tgtBladedWeapon.getBladeName() : bladeName);
        tgtBladedWeapon.setSharpness(bladeSharpness < 0.0 ?
                tgtBladedWeapon.getSharpness() : bladeSharpness);

        tgtBladedWeapon.setRarity(weaponRarity < 0 ?
                tgtBladedWeapon.getRarity() : weaponRarity);
        tgtBladedWeapon.setName(weaponName.isEmpty() || weaponName.isBlank() ?
                tgtBladedWeapon.getName() : weaponName);

        System.out.println("Edit Complete!");
    }

    /**
     * Logic to modify Shop inventory stock
     * @param sc Scanner that can scan the user input.
     * @param inv Inventory object that represents the shop inventory
     */
    private static void modifyStock(Scanner sc, Inventory inv) {
        final String ABORT_STRING = "Modify Stock Aborted!";
        String weaponName;
        try {
            weaponName = getString(sc,
                    "Enter Template Name" +
                            " to modify the stock of");
        } catch (OperationCancelException e) {
            System.out.println(ABORT_STRING);
            return;
        }

        BladedWeapon tgtBladedWeapon;
        try {
            tgtBladedWeapon = getWeapon(weaponName, inv);
        } catch (NoSuchElementException e) {
            System.out.printf(
                    "Template \"%s\" was not found!\n", weaponName);
            return;
        }

        int change;
        try {
            change = getInt(sc, "Enter the amount changed");
        } catch (OperationCancelException e) {
            System.out.println(ABORT_STRING);
            return;
        }

        inv.put(tgtBladedWeapon, Math.max(0, inv.get(tgtBladedWeapon) + change)) ;
        System.out.println("Stock Update Complete!");
    }

    /**
     * Delete template Logic
     * @param sc Scanner that can scan for user input.
     * @param inv Inventory object that represents that shop inventory.
     */
    private static void deleteTemplate(Scanner sc, Inventory inv) {
        System.out.println("==== Remove Template ====");
        String input;
        try {
            input = getString(sc, "Enter the Template Name to remove: ");
        } catch (OperationCancelException e) {
            System.out.println("Template Removal Aborted!");
            return;
        }
        input = input.trim();
        BladedWeapon toRemove;
        try {
            toRemove = getWeapon(input, inv);
        } catch (NoSuchElementException e) {
            System.out.printf("Template \"%s\" could not be found.\n" +
                    "Operation Aborted!", input);
            return;
        }
        System.out.printf("Removing \"%s\"...\n", toRemove.getName());
        inv.remove(toRemove);
        System.out.println("Template Removed!");
    }

    /**
     * Logic to allow testing the performing of one or all Templates.
     * @param sc Scanner that can scan for user input.
     * @param inv Inventory object that represents the shop inventory.
     */
    private static void testStock(Scanner sc, final Inventory inv) {
        System.out.println("==== Testing Weapon Template ====");
        String input ="";
        try {
            input = getString(sc,
                    "Please Enter Template Name to test" +
                            "(Leave Empty for all)", true);
        } catch (OperationCancelException e) {
            System.out.println("Testing Weapon Template Aborted!");
            return;
        }
        input = input.trim();
        if(input.isEmpty()) {
            for(var template : inv.keySet()) {
                template.use();
            }
        } else {
            BladedWeapon toTest;
            try {
                toTest = getWeapon(input, inv);
            } catch (NoSuchElementException e) {
                System.out.printf("Template \"%s\" could not be found!\n", input);
                return;
            }
            toTest.use();
            System.out.println("Template Test Complete!");
        }
    }

    /**
     * Logic to find the template in the shop inventory with name.
     * @param name String of BladedWeapon Template to find
     * @param inv Inventory object that represents the shop inventory.
     * @return BladeWeapon with name
     * @throws NoSuchElementException Exception is thrown if BladedWeapon object
     * with name is not found.
     */
    private static BladedWeapon getWeapon(final String name, final Inventory inv)
        throws NoSuchElementException {
        for (BladedWeapon elem : inv.keySet()) {
            if (elem.getName().equalsIgnoreCase(name)) {
                return elem;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Get user input with expectation of getting a String.
     * Option to allow blank and empty.
     * @param sc Scanner that can scan for user input.
     * @param prompt String that contains the prompt for the user.
     *               Colon ':' is added within function.
     * @param allowEmpty boolean to allow empty or blank strings as valid input.
     * @return String captured user input.
     * @throws OperationCancelException
     * Exception thrown if user inputs the abort code as input.
     */
    private static String getString(Scanner sc, final String prompt,
                                    final boolean allowEmpty)
            throws OperationCancelException {
        String input = "";
        while(true) {
            System.out.print(prompt + ": ");
            input = sc.nextLine();

            if(input.equalsIgnoreCase(QUIT_STRING))
                throw new OperationCancelException();
            else if(!allowEmpty && (input.isBlank() || input.isEmpty()))
                System.out.println(INVALID_INPUT_MSG);
            else
                return input;
        }
    }

    /**
     * Get user input with the expectation of non-empty or non-blank String.
     * @param sc Scanner that can scan for user input.
     * @param prompt String prompt for the user.
     *               Colon ':' is added within the function.
     * @return String captured user input.
     * @throws OperationCancelException
     * Exception thrown if user inputs the abort code as input.
     */
    private static String getString(Scanner sc, final String prompt)
            throws OperationCancelException {
        return getString(sc, prompt, false);
    }

    /**
     * Get user input with the expectation of an int.
     * @param sc Scanner that can scan for user input.
     * @param prompt String prompt for the user.
     *               Colon ':' is added within the function.
     * @return String captured user input.
     * @throws OperationCancelException
     * Exception thrown if user inputs the abort code as input.
     */
    private static int getInt(Scanner sc, final String prompt)
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

    /**
     * Get user input with the expectation of getting a double.
     * @param sc Scanner that can scan for user input.
     * @param prompt String prompt for the user.
     *               Colon ':' is added within the function.
     * @return String captured user input.
     * @throws OperationCancelException
     * Exception thrown if user inputs the abort code as input.
     */
    private static double getDouble(Scanner sc, final String prompt)
        throws OperationCancelException {
        String input = "";
        while(true) {
            System.out.print(prompt + ": ");
            input = sc.nextLine();
            if(input.equalsIgnoreCase(QUIT_STRING))
                throw new OperationCancelException();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MSG);
            }
        }
    }

    /**
     * Function adds a random Bladed Weapon Template with random amount of stock
     * @param inv Inventory object that represents the shop inventory.
     */
    private static void addRandomWeapon(Inventory inv) {
        final int MAX_INIT_STOCK = 10;
        final int MAX_RARITY= 3;
        BladedWeapon newBladedWeapon = new BladedWeapon(
                String.format("RandomWeapon_%d", randWeaponCount++),
                RNG.nextInt(MAX_RARITY) + 1, getRandomHilt(getRandomMaterial()),
                getRandomBlade(getRandomMaterial())
        );
        inv.put(newBladedWeapon, RNG.nextInt(MAX_INIT_STOCK)+1);
        System.out.printf("Weapon \"%s\" added!", newBladedWeapon.getName());
    }

    /**
     * Function returns one of several pre-made Materials
     * @return Material one of several stored in the function.
     */
    private static Material getRandomMaterial() {
        final Material[] PRESET_MAT = {
                new Material("Wood", 600),
                new Material("Iron", 800),
                new Material("Steel", 1000)
        };
        return PRESET_MAT[RNG.nextInt(PRESET_MAT.length)];
    }

    /**
     * Function generates a random blade
     * @param mat Material the random blade should use
     * @return Blade randomly generated that uses provided Material
     */
    private static Blade getRandomBlade(final Material mat) {
        final double MAX_LENGTH = 4.0;
        final double MAX_SHARPNESS = 5.0;
        return new Blade(String.format("RandomBlade_%d", randBladeCount++),
                new Material(mat), RNG.nextDouble() * MAX_LENGTH,
                RNG.nextDouble() * MAX_SHARPNESS);
    }

    /**
     * Function generates a random hilt
     * @param mat Material the random hilt should use
     * @return Hilt randomly generated that uses provided Material
     */
    private static Hilt getRandomHilt(final Material mat) {
        final double MAX_LENGTH = 1.0;
        return new Hilt(String.format("RandomHilt_%d", randHiltCount++),
                new Material(mat), RNG.nextDouble() * MAX_LENGTH);
    }
}
