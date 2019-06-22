package ar.edu.itba.sia;

import ar.edu.itba.sia.Engine.GeneticEngine;
import ar.edu.itba.sia.Engine.Items;
import ar.edu.itba.sia.Exceptions.InvalidParameterException;
import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Game.Item;
import ar.edu.itba.sia.Game.Profession;
import ar.edu.itba.sia.Generics.*;
import ar.edu.itba.sia.Utils.Diversity;
import ar.edu.itba.sia.Utils.JsonManager;
import ar.edu.itba.sia.Utils.ParameterFactories;
import ar.edu.itba.sia.Utils.ReadExcel;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        JSONObject data = JsonManager.readJSON();
        if (data == null) return;

        String professionName = (String) data.get("Class");
        Long populationQty = (Long) data.get("PopulationQty");

        if (!isValidProfession(professionName)) {
            throw new InvalidParameterException(professionName + " is not a valid profession!");
        }

        JSONObject itemsJSON = (JSONObject) data.get("Items");
        Items itemPool = initializeData(itemsJSON);
        JSONObject geneticParameters = (JSONObject) data.get("Genetic Parameters");


        JSONObject combinatorJSON = (JSONObject) geneticParameters.get("Crossover");
        Combinator combinator = ParameterFactories.createCombinator((String) combinatorJSON.get("name"), (Double) combinatorJSON.get("arg"));
        if (combinator == null) {
            throw new InvalidParameterException((String) geneticParameters.get("Crossover") + " is not a valid Crossover method");
        }

        Mutator mutator = ParameterFactories.createMutator((String) geneticParameters.get("Mutator"), itemPool);
        if (mutator == null) {
            throw new InvalidParameterException((String) geneticParameters.get("Mutator") + " is not a valid Mutator method");
        }

        Selector selector1 = ParameterFactories.createSelector((String) geneticParameters.get("Selector1"));
        if (selector1 == null) {
            throw new InvalidParameterException((String) geneticParameters.get("Selector1") + " is not a valid Selector method");
        }

        Selector selector2 = ParameterFactories.createSelector((String) geneticParameters.get("Selector2"));
        if (selector2 == null) {
            throw new InvalidParameterException((String) geneticParameters.get("Selector2") + " is not a valid Selector method");
        }

        Selector selector3 = ParameterFactories.createSelector((String) geneticParameters.get("Selector3"));
        if (selector3 == null) {
            throw new InvalidParameterException((String) geneticParameters.get("Selector3") + " is not a valid Selector method");
        }

        Selector selector4 = ParameterFactories.createSelector((String) geneticParameters.get("Selector4"));
        if (selector4 == null) {
            throw new InvalidParameterException((String) geneticParameters.get("Selector4") + " is not a valid Selector method");
        }

        double ratioA = (double) geneticParameters.get("RatioA");

        double ratioB = (double) geneticParameters.get("RatioB");

        Replacer replacer = ParameterFactories.createReplacer((String) geneticParameters.get("Replacer"), selector1, selector2,
                selector3, selector4, ratioA, ratioB);
        if (replacer == null) {
            throw new InvalidParameterException((String) geneticParameters.get("Replacer") + " is not a valid Replacer method");
        }

        JSONObject conditionerJSON = (JSONObject) geneticParameters.get("Conditioner");
        Conditioner conditioner = ParameterFactories.createConditioner( (String) conditionerJSON.get("name"), (Long) conditionerJSON.get("arg"));
        if (conditioner == null) {
            throw new InvalidParameterException((String) geneticParameters.get("Conditioner") + " is not a valid Conditioner method");
        }

        Diversity diversity = new Diversity();
        List<Species> population = createPopulation(populationQty, professionName, itemPool);
        GeneticEngine engine = new GeneticEngine(combinator, mutator, replacer, conditioner, diversity);
        List<Species> finalPopulation = engine.evolve(population);
    }

    private static boolean isValidProfession(String professionName) {
        for (Profession p : Profession.values()) {
            if (p.name().equals(professionName)) {
                return true;
            }
        }
        return false;
    }

    public static Items initializeData(JSONObject itemsJSON) throws IOException {
        ReadExcel r = new ReadExcel();
        List<Item> weapons = r.getItems((String) itemsJSON.get("Weapons"));
        List<Item> boots = r.getItems((String) itemsJSON.get("Boots"));
        List<Item> helmets = r.getItems((String) itemsJSON.get("Helmets"));
        List<Item> gauntlets = r.getItems((String) itemsJSON.get("Gauntlets"));
        List<Item> chestplates = r.getItems((String) itemsJSON.get("Chestplates"));
        Items itemPool = new Items();
        itemPool.setHelmets(helmets);
        itemPool.setWeapons(weapons);
        itemPool.setChestplates(chestplates);
        itemPool.setGauntlets(gauntlets);
        itemPool.setBoots(boots);
        return itemPool;
    }

    public static List<Species> createPopulation(long quantity, String professionName, Items itemPool) {
        List<Species> population = new LinkedList<>();
        Random random = new Random();
        List<Item> helmets = itemPool.getHelmets();
        List<Item> weapons = itemPool.getWeapons();
        List<Item> chestplates = itemPool.getChestplates();
        List<Item> gauntlets = itemPool.getGauntlets();
        List<Item> boots = itemPool.getBoots();

        for (int i = 0; i < quantity; i++) {
            Item helmet = helmets.get(Math.abs(random.nextInt()) % helmets.size());
            Item weapon = weapons.get(Math.abs(random.nextInt()) % weapons.size());
            Item chestplate = chestplates.get(Math.abs(random.nextInt()) % chestplates.size());
            Item gauntlet = gauntlets.get(Math.abs(random.nextInt()) % gauntlets.size());
            Item boot = boots.get(Math.abs(random.nextInt()) % boots.size());

            GameCharacter character = new GameCharacter(Profession.ARCHER1, helmet, weapon, chestplate,
                    gauntlet, boot, 1.3 + (2.0 - 1.3) * random.nextDouble());
            population.add(character);
        }
        //System.out.println("MAX D:"+d.getMaxDefense()+" MAX AT:"+d.gmaxAtack+" MIN D:"+minDefense+" MAX At:"+maxAtack);

        return population;
    }



}
