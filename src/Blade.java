import java.util.Objects;

public class Blade {
    private Material material;
    private String name;
    private double length;
    private double sharpness;

    public Blade() {
        this.material = new Material();
        name = "Unnamed Blade";
        this.length = Double.NaN;
        this.sharpness = Double.NaN;
    }

    public Blade(String name, Material mat, double length, double sharpness) {
        this.name = name;
        this.material = new Material(mat);
        this.length = length;
        this.sharpness = sharpness;
    }

    public Blade(String name, String material, double length,
                 double sharpness, double density) {
        this.name = name;
        this.material = new Material(material, density);
        this.length = length;
        this.sharpness = sharpness;
    }

    public String getName() { return name; }

    public double getLength() { return length; }

    public double getSharpness() { return sharpness; }

    public double getMass() { return material.getDensity() * length; }

    public double getDensity() { return material.getDensity(); }

    public String getMaterialName() { return material.getName(); }

    public void setName(String name) { this.name = name; }

    public void setMaterial(Material material) { this.material = material; }

    public void setLength(double length) { this.length = length; }

    public void setSharpness(double sharpness) { this.sharpness = sharpness; }

    public void setDensity(double density) { material.setDensity(density); }

    public void setMaterialName(String name) { material.setName(name); }

    @Override
    public String toString() {
        return String.format("Blade \"%s\":\nLength: %f\nSharpness: %f\n%s",
                name, length, sharpness, material.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, name, length, sharpness);
    }

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
