package ar.edu.itba.sia.Game;

public class Item {
    private final String id;
    private final double strength;
    private final double agility;
    private final double expertise;
    private final double resistance;
    private final double hp;

    public Item(String id, double strength, double agility, double expertise, double resistance, double hp) {
        this.id = id;
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.hp = hp;
    }

    public double getStrength() {
        return strength;
    }

    public double getAgility() {
        return agility;
    }

    public double getExpertise() {
        return expertise;
    }

    public double getResistance() {
        return resistance;
    }

    public double getHp() {
        return hp;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        if (Double.compare(item.strength, strength) != 0) {
            return false;
        }
        if (Double.compare(item.agility, agility) != 0) {
            return false;
        }
        if (Double.compare(item.expertise, expertise) != 0) {
            return false;
        }
        if (Double.compare(item.resistance, resistance) != 0) {
            return false;
        }
        if (Double.compare(item.hp, hp) != 0) {
            return false;
        }
        return id != null ? id.equals(item.id) : item.id == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        temp = Double.doubleToLongBits(strength);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(agility);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(expertise);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(resistance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(hp);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return
                "strength=" + strength +
                ", agility=" + agility +
                ", expertise=" + expertise +
                ", resistance=" + resistance +
                ", hp=" + hp ;
    }
}
