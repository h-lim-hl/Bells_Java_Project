import java.util.*;

final class Inventory extends HashMap<Weapon, Integer> {}

public class Main {
    private static final String QUIT_STRING = "q";
    private static final String INVALID_INPUT_MSG = String.format(
            "Invalid input try again!\nEnter '%s' to abort", QUIT_STRING
    );

    private static final Random RNG = new Random(System.currentTimeMillis());
    private static int randBladeCount = 0;
    private static int randHiltCount = 0;
    private static int randWeaponCount = 0;

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
                case 7:
                    addRandomWeapon(stock);
                    break;
                case 8:
                    break;
                default:
                    System.out.println(INVALID_INPUT_MSG);
            }
        } while (!(userChoice == options.length-1));
        System.out.println("Application Exiting... Goodbye!");
    }

    private static void printMenu(final String[] options) {
        System.out.println("==== Welcome to Fantasy Hephaestus's Forge ====");
        System.out.println("Available operations:");
        for (int i = 0; i < options.length; ++i) {
            System.out.printf("(%d) %s\n", i, options[i]);
        }
        System.out.print("Please select an operation: ");
    }

    private static void printInventory (final Inventory inventory) {
        System.out.println("==== Printing Inventory ===");
        inventory.forEach((key, value) -> {
            System.out.println("-------   -------");
            System.out.println(key.toString());
            System.out.printf("Stock left: %d\n", value);
        });
        System.out.println("-------   -------");
        System.out.println("==== Print Complete ====");
    }

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
        inv.put(new Weapon(
                weaponName, weaponRarity,
                hiltName, hiltMatName,
                hiltLength, hiltMatDensity,
                bladeName, bladeMatName, bladeLength,
                bladeSharpness, bladeMatDensity
        ), 0);
        System.out.println("Operation complete!");
        printTemplates(inv);

    }

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

        Weapon tgtWeapon = null;
        try {
            tgtWeapon = getWeapon(weaponName, inv);
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

        tgtWeapon.setHiltDensity(hiltMatDensity < 0.0 ?
                tgtWeapon.getHiltDensity() : hiltMatDensity);
        tgtWeapon.setHiltMaterialName(
                hiltMatName.isEmpty() || hiltMatName.isBlank() ?
                tgtWeapon.getHiltMaterialName() : hiltMatName);
        tgtWeapon.setHiltLength(hiltLength < 0.0 ?
                tgtWeapon.getHiltLength() : hiltLength);
        tgtWeapon.setHiltName(hiltName.isEmpty() ?
                tgtWeapon.getHiltName() : hiltName);

        tgtWeapon.setBladeDensity(bladeMatDensity < 0.0 ?
                tgtWeapon.getBladeDensity() : bladeMatDensity);
        tgtWeapon.setBladeLength(bladeLength < 0.0 ?
                tgtWeapon.getBladeLength() : bladeLength);
        tgtWeapon.setBladeMaterialName(
                bladeMatName.isEmpty() || bladeMatName.isBlank() ?
                tgtWeapon.getBladeMaterialName() : bladeMatName);
        tgtWeapon.setBladeName(bladeName.isEmpty() || bladeMatName.isBlank() ?
                tgtWeapon.getBladeName() : bladeName);
        tgtWeapon.setSharpness(bladeSharpness < 0.0 ?
                tgtWeapon.getSharpness() : bladeSharpness);

        tgtWeapon.setRarity(weaponRarity < 0 ?
                tgtWeapon.getRarity() : weaponRarity);
        tgtWeapon.setName(weaponName.isEmpty() || weaponName.isBlank() ?
                tgtWeapon.getName() : weaponName);

        System.out.println("Edit Complete!");
    }

    private static void modifyStock(Scanner sc, Inventory inv) {
        final String ABORT_STRING = "Modify Stock Aborted!";
        String weaponName;
        try {
            weaponName = getString(sc,
                    "Enter the name of the weapon template" +
                            " to modify the stock of");
        } catch (OperationCancelException e) {
            System.out.println(ABORT_STRING);
            return;
        }

        Weapon tgtWeapon;
        try {
            tgtWeapon = getWeapon(weaponName, inv);
        } catch (NoSuchElementException e) {
            System.out.printf(
                    "Weapon Name \"%s\" was not found!\n", weaponName);
            return;
        }

        int change;
        try {
            change = getInt(sc, "Enter the change amount");
        } catch (OperationCancelException e) {
            System.out.println(ABORT_STRING);
            return;
        }

        inv.put(tgtWeapon, inv.get(tgtWeapon) - change);
        System.out.println("Stock Update Complete!");
    }

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
        Weapon toRemove;
        try {
            toRemove = getWeapon(input, inv);
        } catch (NoSuchElementException e) {
            System.out.printf("Template \"%s\" could not be found.\n" +
                    "Operation Aborted!");
            return;
        }
        inv.remove(toRemove);
        System.out.println("Template Removed!");
    }

    private static void testStock(Scanner sc, final Inventory inv) {
        System.out.println("==== Testing Weapon Template ====");
        String input ="";
        try {
            input = getString(sc,
                    "Please Enter Template Name to test" +
                            "(Leave Empty for all):", true);
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
            Weapon toTest;
            try {
                toTest = getWeapon(input, inv);
            } catch (NoSuchElementException e) {
                System.out.printf("Template \"%s\" could not be found!", input);
                return;
            }
            toTest.use();
            System.out.println("Template Test Complete!");
        }
    }

    private static Weapon getWeapon(final String name, final Inventory inv)
        throws NoSuchElementException {
        for (Weapon elem : inv.keySet()) {
            if (elem.getName().equalsIgnoreCase(name)) {
                return elem;
            }
        }
        throw new NoSuchElementException();
    }

    private static String getString(Scanner sc, final String prompt)
        throws OperationCancelException {
        return getString(sc, prompt, false);
    }

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

    private static void addRandomWeapon(Inventory inv) {
        final int MAX_INIT_STOCK = 10;
        final int MAX_RARITY= 3;
        inv.put(new Weapon(
                String.format("RandomWeapon_%d", randWeaponCount++),
                RNG.nextInt(3) + 1, getRandomHilt(getRandomMaterial()),
                getRandomBlade(getRandomMaterial())
                ), RNG.nextInt(MAX_INIT_STOCK)+1);
    }

    private static Material getRandomMaterial() {
        final Material[] PRESET_MAT = {
                new Material("Wood", 600),
                new Material("Iron", 800),
                new Material("Steel", 1000)
        };
        return PRESET_MAT[RNG.nextInt(PRESET_MAT.length)];
    }

    private static Blade getRandomBlade(final Material mat) {
        final double MAX_LENGTH = 4.0;
        final double MAX_SHARPNESS = 5.0;
        return new Blade(String.format("RandomBlade_%d", randBladeCount++),
                new Material(mat), RNG.nextDouble() * MAX_LENGTH,
                RNG.nextDouble() * MAX_SHARPNESS);
    }

    private static Hilt getRandomHilt(final Material mat) {
        final double MAX_LENGTH = 1.0;
        return new Hilt(String.format("RandomHilt_%d", randHiltCount++),
                new Material(mat), RNG.nextDouble() * MAX_LENGTH);
    }
}
