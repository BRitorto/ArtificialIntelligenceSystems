package ar.edu.itba.sia.Game;

public enum Profession {
    WARRIOR1    (0.6, 1.1, 0.9, 0.8, 1.0, 0.9),
    WARRIOR2    (0.6, 1.2, 1.0, 0.8, 0.8, 0.8),
    WARRIOR3    (0.6, 0.8, 0.9, 0.9, 1.2, 1.1),
    ARCHER1     (0.9, 0.8, 1.1, 1.1, 0.9, 0.7),
    ARCHER2     (0.9, 0.9, 1.1, 1.0, 0.9, 0.8),
    ARCHER3     (0.9, 0.8, 0.8, 0.8, 1.1, 1.2),
    ASSASSIN1   (0.7, 0.8, 1.2, 1.1, 1.0, 0.8),
    ASSASSIN2   (0.7, 0.9, 1.0, 1.1, 1.0, 0.9),
    ASSASSIN3   (0.7, 0.9, 0.9, 1.0, 1.1, 1.0),
    DEFENDER1   (0.1, 1.0, 0.9, 0.7, 1.2, 1.1),
    DEFENDER2   (0.1, 1.1, 0.8, 0.8, 1.1, 1.1),
    DEFENDER3   (0.1, 0.9, 0.9, 0.9, 1.0, 1.3);

    private final double multiplier;
    private final double strength;
    private final double agility;
    private final double expertise;
    private final double resistance;
    private final double hp;

    Profession(double multiplier, double strength, double agility, double expertise, double resistance, double hp) {
        this.multiplier = multiplier;
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.hp = hp;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public double getStrength() {
        return strength;
    }

    public double getAgility() { return agility; }

    public double getExpertise() { return expertise; }

    public double getResistance() {
        return resistance;
    }

    public double getHp() {
        return hp;
    }
}

