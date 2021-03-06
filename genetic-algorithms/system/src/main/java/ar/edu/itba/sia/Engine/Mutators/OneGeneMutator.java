package ar.edu.itba.sia.Engine.Mutators;

import ar.edu.itba.sia.Engine.Items;
import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Game.Item;
import ar.edu.itba.sia.Generics.Mutator;
import ar.edu.itba.sia.Generics.ProbabilityDistribution;

import java.util.List;
import java.util.Random;

public class OneGeneMutator implements Mutator<GameCharacter> {
    private final ProbabilityDistribution probFunc;
    private final Random random;
    private final Items itemPool;

    public OneGeneMutator(ProbabilityDistribution probFunc, Items itemPool) {
        this.probFunc = probFunc;
        this.random = new Random();
        this.itemPool = itemPool;
    }
    @Override
    public GameCharacter mutate(GameCharacter individual) {
        Object[] chromosome = individual.getChromosome();
        int gene = Math.abs(random.nextInt()) % chromosome.length;
        switch (gene) {
            case 0:
                List<Item> helmets = itemPool.getHelmets();
                chromosome[gene] = helmets.get(Math.abs(random.nextInt()) % helmets.size());
                break;
            case 1:
                List<Item> weapons = itemPool.getWeapons();
                chromosome[gene] = weapons.get(Math.abs(random.nextInt()) % weapons.size());
                break;
            case 2:
                List<Item> chestplates = itemPool.getChestplates();
                chromosome[gene] = chestplates.get(Math.abs(random.nextInt()) % chestplates.size());
                break;
            case 3:
                List<Item> gauntlets = itemPool.getGauntlets();
                chromosome[gene] = gauntlets.get(Math.abs(random.nextInt()) % gauntlets.size());
                break;
            case 4:
                List<Item> boots = itemPool.getBoots();
                chromosome[gene] = boots.get(Math.abs(random.nextInt()) % boots.size());
                break;
            case 5:
                chromosome[gene] = MIN_HEIGHT + (MAX_HEIGHT - MIN_HEIGHT) * random.nextDouble();
        }
        GameCharacter mutatedIndividual = new GameCharacter(individual.getProfession(), (Item) chromosome[0],
                (Item) chromosome[1],(Item) chromosome[2], (Item) chromosome[3],
                (Item) chromosome[4], (double) chromosome[5]);
        return mutatedIndividual;
    }

    @Override
    public boolean shouldMutate(GameCharacter individual, long generation) {
        return random.nextDouble() <= this.probFunc.function(individual, generation);
    }



    @Override
    public String getClassName(){
        return "OneGeneMutator";
    }
}
