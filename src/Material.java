import java.util.Objects;

public class Material {
    private double density;
    private String name;

    public Material() {
        density = Double.NaN;
        name = "Unknown";
    }

    public Material(String name, double density) {
        this.density = density;
        this.name = name;
    }

    public Material(Material other) {
        density = other.density;
        name = other.name;
    }

    public double getDensity() { return density; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public void setDensity(double density) { this.density = density; }

    @Override
    public String toString() {
        return String.format("Material \"%s\":\nDensity: %f\n", name, density);
    }

    @Override
    public int hashCode() {
        return Objects.hash(density, name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Material om)) return false;
        return Double.compare(density, om.density) == 0
                && name.equals(om.name);
    }

}
