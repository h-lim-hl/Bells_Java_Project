import java.util.Objects;

/**
 * This is a Hilt component class for Bladed Weapon.
 * This class serves as a class that represents all hilts.
 */
public class Hilt {
    /**
     * Minimum length for a hilt
     */
    private final double MIN_LENGTH = 0.1;

    private Material material;
    private String name;
    private double length;

    /**
     * Default Constructor for Hilt class.
     * !!! WARNING !!!
     * Default constructed Hilt contains invalid data!
     */
    protected Hilt() {
        length = Double.NaN;
        name = "Unnamed Hilt";
        material = new Material("Immaterial", Double.NaN);
    }

    /**
     * Constructor for Hilt class
     * @param name String to be the Hilt Template name.
     *             String will be trimmed before saving.
     * @param mat Material object to be the material for the hilt.
     *            Material construction rules apply.
     * @param length double to be the length of the hilt in metres.
     *               length will be Math.abs() then Math.max() with MIN_LENGTH
     * @see Material
     */
    public Hilt(String name, Material mat, double length) {
        this.name = name.trim();
        this.material = new Material(mat);
        this.length = Math.max(Math.abs(length), MIN_LENGTH);
    }

    /**
     * Constructor for Hilt class (verbose)
     * @param name String to be the Hilt Template name.
     *             String will be trimmed before saving.
     * @param material String to be the Material name.
     *                 Material naming rules apply.
     * @param length double to be the length of the hilt in metres.
     *               length will be Math.abs() then Math.max() with MIN_LENGTH
     * @param density double to be the Material density.
     *                Material density rules apply.
     * @see Material
     */
    public Hilt(String name, String material, double length, double density) {
        this.name = name;
        this.material = new Material(material, density);
        this.length = length;
    }

    /**
     * Copy Constructor for Hilt Class
     * @param other Hilt to copy from
     */
    public Hilt(final Hilt other) {
        this.name = other.name;
        this.length = other.length;
        this.material = new Material(other.material);
    }
    /**
     * Getter for Hilt name
     * @return String that is the name of the Hilt Template.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for Hilt length
     * @return double that represents the length in metres.
     */
    public double getLength() {
        return length;
    }

    /**
     * Getter for the Hilt Material density
     * @return double that is the Hilt's Material density.
     * @see Material
     */
    public double getDensity() {
        return material.getDensity();
    }

    /**
     * Getter for the Hilt Material name
     * @return String that is the name of the Hilt Material.
     * @see Material
     */
    public String getMaterialName() {
        return material.getName();
    }

    /**
     * Setter for the Hilt Template name
     * @param name String that will be the new name.
     *             name will be trimmed before saving.
     */
    public void setName(String name) {
        this.name = name.trim();
    }

    /**
     * Setter for the Hilt's length
     * @param length double that will be the new length in metres.
     *               length will be Math.abs() then Math.max() with MIN_LENGTH.
     */
    public void setLength(double length) {
        this.length = Math.max(Math.abs(length),MIN_LENGTH);
    }

    /**
     * Setter for Hilt Material density
     * @param density double that will be the new Material density.
     *                Material density rules apply.
     * @see Material
     */
    public void setDensity(double density) {
        material.setDensity(density);
    }

    /**
     * Setter for Hilt Material name
     * @param name String that will be the new name for Hilt Material.
     *                 Material naming rules apply.
     * @see Material
     */
    public void setHiltMaterialName(String name) {
        this.material.setName(name);
    }

    /**
     * Setter to replace Hilt's Material
     * @param material Material that will be the Hilt's new Material
     * @see Material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Getter to get the Hilt's Mass in gram * metre
     * @return double that is the gram mass of the Hilt.
     */
    public double getMass() {
        return material.getDensity() * length;
    }

    /**
     * Hilt implementation of toString Method
     * @return String that contains data within Hilt.
     */
    @Override
    public String toString() {
        return String.format("Hilt \"%s\":\nLength: %f\n%s",
                name, length, material.toString());
    }

    /**
     * Hilt implementation of hashCode Method
     * @return int that is the calculated hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(material, name, length);
    }

    /**
     * Hilt implementation of equals method.
     * @param other The other object to check if equal.
     * @return If the other Object is itself or equal to
     * the Hilt object being checked with.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Hilt oh)) return false;
        return Double.compare(length, oh.length) == 0
                && name.equals(oh.name)
                && material.equals(oh.material);
    }
}
