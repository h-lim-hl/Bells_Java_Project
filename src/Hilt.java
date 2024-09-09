import java.util.Objects;

public class Hilt {
    private Material material;
    private String name;
    private double length;

    public Hilt () {
        length = Double.NaN;
        name = "Unnamed Hilt";
        material = new Material("Immaterial", Double.NaN);
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

    public String getMaterialName () {
        return material.getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setDensity(double density){
        material.setDensity(density);
    }

    public void setMaterial(String material){
        this.material.setName(material);
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getMass() {
        return material.density * length;
    }

    @Override public String toString() {
        return String.format( "Hilt \"%s\":\nlength: %f\n%s\n",
                name, length, material.toString());
    }

    @Override public int hashCode() {
        return Objects.hash(material, name, length);
    }

}
