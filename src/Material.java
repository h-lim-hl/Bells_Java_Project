import java.util.Objects;

/**
 * This is a Material class for intended for all components.
 * This class serves as a class that represents all material.
 */
public class Material {
    private final double MIN_DENSITY = 0.001;

    private double density;
    private String name;

    /**
     * Default Constructor for Material class.
     * !!! WARNING !!!
     * Default constructed Material contains invalid data!
     */
    protected Material() {
        density = Double.NaN;
        name = "Unknown";
    }

    /**
     * Constructor for Material class
     * @param name String to be the name of the Material Template.
     *             name will be trimmed before saving.
     * @param density double that is the density in grams/metre
     *                density will be Math.abs() then Math.max() with MIN_DENSITY
     */
    public Material(String name, double density) {
        this.density = Math.max(Math.abs(density), MIN_DENSITY);
        this.name = name.trim();
    }

    /**
     * Copy Constructor for Material Class
     * @param other Material to copy from
     */
    public Material(Material other) {
        density = other.density;
        name = other.name;
    }

    /**
     * Getter for Material density
     * @return double Density in grams
     */
    public double getDensity() { return density; }

    /**
     * Getter for Material name
     * @return String that is the name of the Material.
     */
    public String getName() { return name; }

    /**
     * Setter for Material name
     * @param name String will be the new name for the Material
     *             name will be trimmed before saving.
     */
    public void setName(String name) { this.name = name.trim(); }

    /**
     * Setter for Material density
     * @param density double will be the new density in grams.
     *                density will be Math.abs() then Math.max() with MIN_DENSITY
     */
    public void setDensity(double density) {
        this.density = Math.max(Math.abs(density),MIN_DENSITY);
    }

    /**
     * Material implementation of toString method
     * @return String that contains information of data within Material
     */
    @Override
    public String toString() {
        return String.format("Material \"%s\":\nDensity: %f\n", name, density);
    }

    /**
     * Material implementation of hashCode method
     * @return int that is the calculated hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(density, name);
    }

    /**
     * Material implementation of equals method.
     * @param other The other object to check if equal.
     * @return If the other Object is itself or equal to
     * the Material object being checked with.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Material om)) return false;
        return Double.compare(density, om.density) == 0
                && name.equals(om.name);
    }

}
