package ar.edu.itba.sia.Game;

import ar.edu.itba.sia.Engine.Items;
import ar.edu.itba.sia.Exceptions.IllegalChromoseException;
import ar.edu.itba.sia.Generics.Species;

import java.util.Arrays;

public class GameCharacter implements Species {
    private static final int CHROMOSOME_SIZE = 6;
    private final Profession profession;
    private final Item[] items;
    private final double height;
    private double attack;
    private double defense;
    private Object[] chromosome;

    public GameCharacter(Profession profession, Item helmet, Item weapon, Item chestplate, Item gauntlets, Item boots,
                         double height) {
        this.items = new Item[] {helmet, weapon, chestplate, gauntlets, boots};
        this.height = height;
        this.profession = profession;
        computeAttributes();
        this.chromosome= new Object[] {this.items[0], this.items[1], this.items[2], this.items[3], this.items[4], this.height };
    }


    public GameCharacter(Profession profession, Object[] chromosome){
        this.profession = profession;

        if(chromosome.length != CHROMOSOME_SIZE){
            throw new IllegalChromoseException(CHROMOSOME_SIZE);
        }

        this.items = new Item[]{
                (Item)chromosome[0], (Item)chromosome[1],
                (Item)chromosome[2], (Item)chromosome[3], (Item)chromosome[4]};

        this.height = (Double) chromosome[5];
        computeAttributes();
        this.chromosome = chromosome;

    }

    private void computeAttributes(){
        double strength = computeStrength();
        double agility = computeAgility();
        double expertise = computeExpertise();
        double resistance = computeResistance();
        double hp = computeHp();

        this.attack = (agility + expertise) * strength * computeATM(height);
        this.defense = (resistance + expertise) * hp * computeDEM(height);
    }

    public double getMultiplier() {
        return this.getProfession().getMultiplier();
    }

    private double computeStrength() {
        double sumStrength = Arrays.stream(items).mapToDouble(Item::getStrength).sum();
        return 100 * Math.tanh(0.01 * sumStrength * profession.getStrength());
    }

    private double computeAgility() {
        double sumAgility = Arrays.stream(items).mapToDouble(Item::getAgility).sum();
        return Math.tanh(0.01 * sumAgility * profession.getAgility());
    }

    private double computeExpertise() {
        double sumExpertise = Arrays.stream(items).mapToDouble(Item::getExpertise).sum();
        return 0.6 * Math.tanh(0.01 * sumExpertise * profession.getExpertise());
    }

    private double computeResistance() {
        double sumResistance = Arrays.stream(items).mapToDouble(Item::getResistance).sum();
        return Math.tanh(0.01 * sumResistance * profession.getResistance());
    }

    private double computeHp() {
        double sumHp = Arrays.stream(items).mapToDouble(Item::getHp).sum();
        return 100 * Math.tanh(0.01 * sumHp * profession.getHp());
    }

    private static double computeATM(double height) {
        return 0.5 - Math.pow(3. * height - 5, 4) + Math.pow(3. * height - 5, 2) + height * 0.5;
    }

    private static double computeDEM(double height) {
        return 2. + Math.pow(3. * height - 5, 4) - Math.pow(3. * height - 5, 2) - height * 0.5;
    }

    public Item[] getItems() {
        return this.items;
    }

    public Profession getProfession() {
        return this.profession;
    }

    public double getHeight() {
        return this.height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameCharacter that = (GameCharacter) o;
        if (Double.compare(that.height, height) != 0) {
            return false;
        }
        if (profession != that.profession) {
            return false;
        }
        //return Arrays.deepEquals(items, that.items);
        for(int i = 0; i< items.length; i++){
            if(!items[i].equals(that.items[i])){
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = profession.hashCode();
        result = 31 * result + Arrays.deepHashCode(items);
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public double getFitness() {
        return getMultiplier() * this.attack + (1 - getMultiplier()) * this.defense;
    }


    @Override
    public double getBoltzmannFitness(double denominator, double temperature){
        return (Math.exp(this.getFitness() / temperature)) / denominator;
    }

    @Override
    public Object deepCopy() {
        return new GameCharacter(this.profession, this.items[0], this.items[1], this.items[2], this.items[3], this.items[4], this.height);
    }

    public Object[] getChromosome(){
        return this.chromosome;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }


    @Override
    public String toString() {
      //  helmet, weapon, chestplate, gauntlets, boots
        StringBuilder it= new StringBuilder();

        it.append("\t\tHelmet: ").append(items[0]);
        it.append("\n\t\tWeapon: ").append(items[1]);
        it.append("\n\t\tChestplate: ").append(items[2]);
        it.append("\n\t\tGauntlets: ").append(items[3]);
        it.append("\n\t\tBoots: ").append(items[4]);
        return
                "Profession=" + profession +"\n"+
                "Items=\n" + it +"\n"+
                "Height=" + height +"\n"+
                "Attack=" + attack +"\n"+
                "Defense=" + defense +"\n";
    }
}
