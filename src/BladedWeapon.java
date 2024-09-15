import java.util.Objects;

/**
 * Class that represents all single BladedWeapon in this world
 */
public class BladedWeapon extends Weapon {
    private Hilt hilt;
    private Blade blade;

    /**
     * Default Constructor for BladedWeapon class
     * !!! WARNING !!!
     * Default constructed BladeWeapon contains invalid data!
     */
    protected BladedWeapon() {
        super();
        this.hilt = new Hilt();
        this.blade = new Blade();
    }

    /**
     * Constructor for BladedWeapon class
     * @param name String that will be the name of new BladedWeapon.
     *             Weapon name rules apply.
     * @param rarity int Rarity rating of new BladedWeapon.
     *               Weapon rarity rules apply.
     * @param hilt Hilt that will be the Hilt of new BladedWeapon
     * @param blade Blade that will be the Blade of new BladedWeapon
     * @see Weapon
     * @see Hilt
     * @see Blade
     */
    public BladedWeapon(String name, int rarity, Hilt hilt, Blade blade) {
        super(rarity, name);
        this.name = name;
        this.rarity = rarity;
        this.hilt = new Hilt(hilt);
        this.blade = new Blade(blade);
    }

    /**
     * BladedWeapon Constructor (verbose)
     * @param weaponName String that will be the name of new BladedWeapon.
     *                   Weapon name rules apply.
     * @param rarity int Rarity rating of new BladedWeapon.
     *               Weapon rarity rules apply.
     * @param hiltName String to be the Hilt Template name.
     *                 String will be trimmed before saving.
     * @param hiltMaterial  String to be the Hilt Material name.
     *                      Material naming rules apply.
     * @param hiltLength double length of the hilt.
     *                   Hilt length rules apply.
     * @param hiltDensity double to be the Hilt Material density.
     *                    Material density rules apply.
     * @param bladeName String name for the Blade template.
     *                  Blade naming rules apply.
     * @param bladeMaterial String name for Blade Material.
     *                      Material naming rules observed.
     * @param bladeLength double length of the blade.
     *                    Blade length rules apply.
     * @param bladeSharpness The sharpness rating for the blade.
     *                       Blade sharpness rules apply.
     * @param bladeDensity The density of the material used for the blade.
     *                     Material density rules observed.
     * @see Weapon
     * @see Hilt
     * @see Blade
     */
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

    /**
     * Copy Constructor for BladedWeapon Class
     * @param other BladedWeapon to copy from
     */
    public BladedWeapon(final BladedWeapon other) {
        name = other.name;
        rarity = other.rarity;
        this.hilt = new Hilt(other.hilt);
        this.blade = new Blade(other.blade);
    }

    /**
     * Getter for Blade's Sharpness
     * @return double that represents the sharpness rating.
     */
    public double getSharpness() {
        return blade.getSharpness();
    }

    /**
     * Getter for Blade's length in metres.
     * @return double that represents the length in metres.
     */
    public double getBladeLength() {
        return blade.getLength();
    }

    /**
     * Getter for Blade's Material density
     * @return double Density of the Material
     * @see Material
     */
    public double getBladeDensity() {
        return blade.getDensity();
    }

    /**
     * Getter for Blade's Material name
     * @return String materialName
     * @see Material
     */
    public String getBladeMaterialName() {
        return blade.getMaterialName();
    }

    /**
     * Getter for Blade's name
     * @return String that presents the name.
     */
    public String getBladeName() {
        return blade.getName();
    }

    /**
     * Getter for Hilt name
     * @return String that is the name of the Hilt Template.
     */
    public String getHiltName() {
        return hilt.getName();
    }

    /**
     * Getter for Hilt length
     * @return double that represents the length in metres.
     */
    public double getHiltLength() {
        return hilt.getLength();
    }

    /**
     * Getter for the Hilt Material density
     * @return double that is the Hilt's Material density.
     * @see Material
     */
    public double getHiltDensity() {
        return hilt.getDensity();
    }

    /**
     * Getter for the Hilt Material name
     * @return String that is the name of the Hilt Material.
     * @see Material
     */
    public String getHiltMaterialName() {
        return hilt.getMaterialName();
    }

    /**
     * Setter for Blade's Sharpness
     * @param sharpness double rating to set the new sharpness.
     *                  Blade sharpness rules apply.
     * @see Blade
     */
    public void setSharpness(double sharpness) {
        blade.setSharpness(sharpness);
    }

    /**
     * Setter for Blade's length
     * @param length double in metres to set the new length to.
     *               Blade length rules apply.
     * @see Blade
     */
    public void setBladeLength(double length) {
        blade.setLength(length);
    }

    /**
     * Setter for Blade's Material's density
     * @param density double Density to set the Blade's Material's density.
     *                Material class setDensity() rules apply.
     * @see Material
     */
    public void setBladeDensity(double density) {
        blade.setDensity(density);
    }

    /**
     * Setter for Blade's Material's name
     * @param name String name to rename the Blade's Material's name.
     *             Material class setName() rules apply.
     * @see Material
     */
    public void setBladeMaterialName(String name) {
        blade.setMaterialName(name);
    }

    /**
     * Setter for Blade's name
     * @param name String to rename the Blade object.
     *             Blade naming rules apply.
     * @see Blade
     */
    public void setBladeName(String name) {
        blade.setName(name);
    }

    /**
     * Setter for Hilt Material density
     * @param density double that will be the new Material density.
     *                Material density rules apply.
     * @see Material
     */
    public void setHiltDensity(double density) {
        hilt.setDensity(density);
    }

    /**
     * Setter for the Hilt's length
     * @param length double that will be the new length in metres.
     *               Hilt length rules apply.
     */
    public void setHiltLength(double length) {
        hilt.setLength(length);
    }

    /**
     * Setter for Hilt Material name
     * @param name String that will be the new name for Hilt Material.
     *                 Material naming rules apply.
     * @see Material
     */
    public void setHiltMaterialName(String name) {
        hilt.setHiltMaterialName(name);
    }

    /**
     * Setter for the Hilt Template name
     * @param name String that will be the new name.
     *             name will be trimmed before saving.
     */
    public void setHiltName(String name) {
        hilt.setName(name);
    }

    /**
     * Setter for BladedWeapon Hilt.
     * @param hilt Hilt to be the new Hilt for BladedWeapon.
     */
    public void setHilt(final Hilt hilt) {
        this.hilt = new Hilt(hilt);
    }

    /**
     * Setter for BladedWeapon Blade.
     * @param blade Blade to be the new Blade for BladedWeapon
     */
    public void setBlade(final Blade blade) {
        this.blade = new Blade(blade);
    }

    /**
     *  Usable interface use Function implementation.
     *  Simulates damage done if a weapon of this template was swung.
     * @see Usable
     */
    @Override
    public void use() {
        double damage = (hilt.getMass() + blade.getMass()
                * blade.getSharpness() * rarity) / 1000;
        double reach = hilt.getLength() + blade.getLength();
        System.out.printf("%s swings for %f damage with a reach of %f\n",
                name, damage, reach);
    }

    /**
     * BladedWeapon implementation of toString Method
     * @return String that contains data within BladedWeapon.
     */
    //    public
    @Override
    public String toString() {
        return String.format("Weapon \"%s\":\nRarity: %d\n\n%s\n%s",
                name, rarity, blade.toString(), hilt.toString());
    }

    /**
     * BladedWeapon implementation of hashCode Method
     * @return int that is the calculated hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(hilt, blade, name, rarity);
    }

    /**
     * BladedWeapon implementation of equals method.
     * @param other The other object to check if equal.
     * @return If the other Object is itself or equal to
     * the BladedWeapon object being checked with.
     */
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
