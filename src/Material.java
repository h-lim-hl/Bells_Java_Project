import java.util.Objects;

public class Material {
    protected double density;
    protected String name;

    public Material() {
        density = Double.NaN;
        name = "Unknown";
    }

    public Material(String name, double density) {
        this.density = density;
        this.name = name;
    }

    public double getDensity() {
        return density;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public String toString() {
        return String.format("Material \"%s\":\nDensity: %f\n", name, density);
    }

    @Override public int hashCode () {
        return Objects.hash(density, name);
    }

    @Override public boolean equals(Object other) {
        if(other == this) return true;
        if(!(other instanceof Material om)) return false;
        return Double.compare(density, om.density) == 0
                && name.equals(om.name);
    }

}
