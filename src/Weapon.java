import java.util.Objects;

/**
 * Represents an abstract Weapon class.
 * This class serves as a base class for all specific types of weapons.
 */
public abstract class Weapon implements Usable {
    /**
     * rarity the rarity rating of the weapon.
     * Non-negative numbers only.
     */
    protected int rarity;
    /**
     * name String of the Weapon
     */
    protected String name;

    /**
     * Default Constructor for Weapon class.
     */
    public Weapon() {
        rarity = 0;
        name = "Unnamed Weapon";
    }

    /**
     * Constructor for Weapon class
     * @param rarity the rarity rating of the weapon.
     *              Non-negative numbers only.
     * @param name The name for the weapon.
     *             pre and post trailing whitespaces are trimmed.
     */
    public Weapon(int rarity, String name) {
        this.rarity = Math.max(0, rarity);
        this.name = name.trim();
    }

    /**
     * Copy Constructor for Weapon Class
     * @param other Weapon to copy from
     */
    public Weapon(final Weapon other) {
        this.name = other.name;
        this.rarity = other.rarity;
    }
    /**
     * Getter for Weapon's rarity.
     * @return int that represents the rarity.
     */
    public int getRarity() {
        return rarity;
    }

    /**
     * Getter for Weapon's name.
     * @return String that represents the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for Weapon's rarity.
     * @param rarity int that represents the rarity.
     *               Negative numbers will be taken as *Zero*.
     */
    public void setRarity(int rarity) {
        this.rarity = Math.max(0, rarity);
    }

    /**
     * Setter for Weapon's name
     * @param name String that represents the name.
     *             Given String will be trimmed before saving.
     */
    public void setName(String name) {
        this.name = name.trim();
    }

    /**
     * Weapon implementation of toString method.
     * @return String that contains the data in the weapon object.
     */
    @Override
    public String toString() {
        return String.format("Weapon %s\nRarity: %d\n", name, rarity);
    }

    /**
     * Weapon implementation of hashCode method.
     * @return int that is the calculated hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(rarity, name);
    }

    /**
     * Weapon implementation of equals method.
     * @param other The other object to check if equal.
     * @return If the other Object is itself or equal to
     * the Weapon object being checked with.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Weapon ow)) return false;
        return rarity == ow.rarity
                && name.equals(ow.name);
    }
}
