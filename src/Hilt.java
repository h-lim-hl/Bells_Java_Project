import java.util.Objects;

public class Hilt {
    private Material material;
    private String name;
    private double length;

    public Hilt() {
        length = Double.NaN;
        name = "Unnamed Hilt";
        material = new Material("Immaterial", Double.NaN);
    }

    public Hilt(String name, Material mat, double length) {
        this.name = name;
        this.material = new Material(mat);
        this.length = length;
    }

    public Hilt(String name, String material, double length, double density) {
        this.name = name;
        this.material = new Material(material, density);
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public double getDensity() {
        return material.getDensity();
    }

    public String getMaterialName() {
        return material.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setDensity(double density) {
        material.setDensity(density);
    }

    public void setHiltMaterial(String material) {
        this.material.setName(material);
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getMass() {
        return material.getDensity() * length;
    }

    @Override
    public String toString() {
        return String.format("Hilt \"%s\":\nLength: %f\n%s",
                name, length, material.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, name, length);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Hilt oh)) return false;
        return Double.compare(length, oh.length) == 0
                && name.equals(oh.name)
                && material.equals(oh.material);
    }
}
