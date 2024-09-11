import java.util.Objects;

/**
 * This is a Blade component class for Bladed Weapon.
 * This class serves as a class that represents all blades.
 */
public class Blade {
    private final double MIN_LENGTH = 0.1;
    private final double MIN_SHARPNESS = 1.0;

    private Material material;
    private String name;
    private double length;
    private double sharpness;

    /**
     * Default Constructor for Blade class.
     * !!! WARNING !!!!
     * Default constructed Blade contains invalid data!
     */
    protected Blade() {
        this.material = new Material();
        name = "Unnamed Blade";
        this.length = Double.NaN;
        this.sharpness = Double.NaN;
    }

    /**
     * Constructor for Blade class
     * @param name The name for the blade template
     * @param mat The Material Object that represents the material for the blade
     * @param length The length of the blade in metres
     * @param sharpness The sharpness rating for the blade.
     *                  Entire range of int is accepted but
     *                  positive numbers preferred.
     */
    public Blade(String name, Material mat, double length, double sharpness) {
        this.name = name;
        this.material = new Material(mat);
        this.length = Math.abs(length);
        this.sharpness = Math.abs(sharpness);
    }

    /**
     * Constructor for Blade Class (verbose)
     * @param name String name for the Blade template.
     *             The String will be trimmed before saving.
     * @param material String name for the material for the blade.
     *                 Material naming rules observed.
     * @param length double length of the blade in metres. (Min 0.1)
     *               Negative numbers will be taken as positive.
     * @param sharpness The sharpness rating for the blade. (Min 1.0)
     * @param density The density of the material used for the blade.
     *                Material density rules observed.
     * @see Material
     */
    public Blade(String name, String material, double length,
                 double sharpness, double density) {
        this.name = name.trim();
        this.material = new Material(material, density);
        this.length = Math.max(Math.abs(length), MIN_LENGTH);
        this.sharpness = Math.max(Math.abs(sharpness), MIN_SHARPNESS);
    }

    /**
     * Getter for Blade's name
     * @return String that presents the name.
     */
    public String getName() { return name; }

    /**
     * Getter for Blade's length in metres.
     * @return double that represents the length in metres.
     */
    public double getLength() { return length; }

    /**
     * Getter for Blade's Sharpness
     * @return double that represents the sharpness rating.
     */
    public double getSharpness() { return sharpness; }

    /**
     * Getter for Blade's Mass
     * Calculates using material density * blade length
     * @return double that represents the blades mass in grams
     */
    public double getMass() { return material.getDensity() * length; }

    /**
     * Getter for Blade's Material density
     * @return double Density of the Material
     * @see Material
     */
    public double getDensity() { return material.getDensity(); }

    /**
     * Getter for Blade's Material name
     * @return String materialName
     * @see Material
     */
    public String getMaterialName() { return material.getName(); }

    /**
     * Setter for Blade's name
     * @param name String to rename the Blade object.
     *             String will be trimmed before saving.
     */
    public void setName(String name) { this.name = name.trim(); }

    /**
     * Setter for Blade's Material
     * @param material Material Object to replace the old.
     */
    public void setMaterial(Material material) { this.material = material; }

    /**
     * Setter for Blade's length
     * @param length Double in metres to set the new length to.
     *               length will be Math.abs() then Math.max() with MIN_LENGTH .
     */
    public void setLength(double length) {
        this.length = Math.max(Math.abs(length), MIN_LENGTH);
    }

    /**
     * Setter for Blade's Sharpness
     * @param sharpness double rating to set the new sharpness.
     *                  sharpness will be Math.abs() then Math.max() with MIN_SHARPNESS
     */
    public void setSharpness(double sharpness) {
        this.sharpness = Math.max(Math.abs(sharpness), MIN_SHARPNESS);
    }

    /**
     * Setter for Blade's Material's density
     * @param density double Density to set the Blade's Material's density.
     *                Material class setDensity() rules apply.
     * @see Material
     */
    public void setDensity(double density) { material.setDensity(density); }

    /**
     * Setter for Blade's Material's name
     * @param name String name to rename the Blade's Material's name.
     *             Material class setName() rules apply.
     * @see Material
     */
    public void setMaterialName(String name) { material.setName(name); }

    /**
     * Blade implementation of toString method.
     * @return String that contains the information within Blade object.
     */
    @Override
    public String toString() {
        return String.format("Blade \"%s\":\nLength: %f\nSharpness: %f\n%s",
                name, length, sharpness, material.toString());
    }

    /**
     * Blade implementation of hashCode method
     * @return int that is the calculated hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(material, name, length, sharpness);
    }

    /**
     * Blade implementation of equals method.
     * @param other The other object to check if equal.
     * @return If the other Object is itself or equal to
     * the Blade object being checked with.
     */
    @Override
    public boolean equals(Object other) {
        if(other == this) return true;
        if(!(other instanceof Blade ob)) return false;
        return Double.compare(length, ob.length) == 0
                && Double.compare(sharpness, ob.sharpness) == 0
                && name.equals(ob.name)
                && material.equals(ob.material);
    }
}
