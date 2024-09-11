import java.util.Objects;

public class BladedWeapon extends Weapon {
    private Hilt hilt;
    private Blade blade;

    //    String
    public BladedWeapon() {
        super();
        this.hilt = new Hilt();
        this.blade = new Blade();
    }

    public BladedWeapon(String name, int rarity, Hilt hilt, Blade blade) {
        super(rarity, name);
        this.name = name;
        this.rarity = rarity;
    }

    public BladedWeapon(
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

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public double getSharpness() {
        return blade.getSharpness();
    }

    public double getBladeLength() {
        return blade.getLength();
    }

    public double getBladeDensity() {
        return blade.getDensity();
    }

    public String getBladeMaterialName() {
        return blade.getMaterialName();
    }

    public String getBladeName() {
        return blade.getName();
    }

    public String getHiltName() {
        return hilt.getName();
    }

    public double getHiltLength() {
        return hilt.getLength();
    }

    public double getHiltDensity() {
        return hilt.getDensity();
    }

    public String getHiltMaterialName() {
        return hilt.getMaterialName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void setSharpness(double sharpness) {
        blade.setSharpness(sharpness);
    }

    public void setBladeLength(double length) {
        blade.setLength(length);
    }

    public void setBladeDensity(double density) {
        blade.setDensity(density);
    }

    public void setBladeMaterialName(String name) {
        blade.setMaterialName(name);
    }

    public void setBladeName(String name) {
        blade.setName(name);
    }

    public void setHiltDensity(double density) {
        hilt.setDensity(density);
    }

    public void setHiltLength(double length) {
        hilt.setLength(length);
    }

    public void setHiltMaterialName(String name) {
        hilt.setHiltMaterial(name);
    }

    public void setHiltName(String name) {
        hilt.setName(name);
    }

    public void setHilt(Hilt hilt) { this.hilt = hilt; }

    public void setBlade(Blade blade) { this.blade = blade; }

    @Override
    public void use() {
        double damage = (hilt.getMass() + blade.getMass()
                * blade.getSharpness() * rarity) / 1000;
        double reach = hilt.getLength() + blade.getLength();
        System.out.printf("%s swings for %f damage with a reach of %f\n",
                name, damage, reach);
    }

    //    public
    @Override
    public String toString() {
        return String.format("Weapon \"%s\":\nRarity: %d\n\n%s\n%s",
                name, rarity, blade.toString(), hilt.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hilt, blade, name, rarity);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof BladedWeapon ow)) return false;
        return rarity == ow.rarity
                && name.equals(ow.name)
                && blade.equals(ow.blade)
                && hilt.equals(ow.hilt);
    }
}
