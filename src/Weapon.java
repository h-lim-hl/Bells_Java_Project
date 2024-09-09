import java.util.Objects;

public class Weapon implements Usable {
    Hilt hilt;
    Blade blade;
    String name;
    int rarity;

    //    String
    public Weapon() {
        this.hilt = new Hilt();
        this.blade = new Blade();
        this.name = "Invalid Weapon";
        this.rarity = -1;
    }

    public Weapon(String name, int rarity, Hilt hilt, Blade blade) {
        this.name = name;
        this.rarity = rarity;
        this.hilt = hilt;
        this.blade = blade;
    }

    public Weapon(
            String weaponName, int rarity,
            String hiltName, String hiltMaterial,
            double hiltLength, double hiltDensity,
            String bladeName, String bladeMaterial, double bladeLength,
            double bladeSharpness, double bladeDensity) {
        this.name = weaponName;
        this.rarity = rarity;
        this.hilt = new Hilt(hiltName, hiltMaterial, hiltLength, hiltDensity);
        this.blade = new Blade(bladeName, bladeMaterial, bladeLength,
                bladeSharpness, bladeDensity);
    }

    @Override
    public void use() {
        double damage = (hilt.getMass() + blade.getMass()
                * blade.getSharpness() * rarity);
        double reach = hilt.getLength() + blade.getLength();
        System.out.printf("%s swings for %f damage with a reach of %f",
                name, damage, reach);
    }

    @Override
    public String toString() { //TODO
        return String.format("Weapon \"%s\":\nrarity: %d\n%s%s\n",
                name, rarity, blade.toString(), hilt.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hilt, blade, name, rarity);
    }

}
