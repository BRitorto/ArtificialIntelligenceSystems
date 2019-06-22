package ar.edu.itba.sia.Utils;

import ar.edu.itba.sia.Engine.Items;
import ar.edu.itba.sia.Generics.Combinator;
import ar.edu.itba.sia.Generics.Mutator;
import ar.edu.itba.sia.Generics.Replacer;
import ar.edu.itba.sia.Generics.Selector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonManager {

    JSONParser jsonParser;

    public JsonManager(){
        jsonParser = new JSONParser();
    }

    @SuppressWarnings("unchecked")
    public static JSONObject readJSON() {
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("character.json")){
            Object obj = jsonParser.parse(reader);

            return (JSONObject) obj;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public void createJSON(Items itemPool, Combinator combinator, Mutator mutator, Selector selector, Replacer replacer, String characterClass){
        /*
        JSONObject characterDetails = new JSONObject();

        characterDetails.put("firstName", "Elodin");
        characterDetails.put("class", "archer");

        JSONObject characterStats = new JSONObject();
        characterStats.put("attack", 2);
        characterStats.put("defense", 3);
        characterStats.put("strength", 1);
        characterStats.put("stamina", 4);

        characterDetails.put("stats", characterStats);
        */
        JSONObject geneticParameters = new JSONObject();
        geneticParameters.put("Crossover", combinator.getClassName());
        geneticParameters.put("Mutator", mutator.getClassName());
        geneticParameters.put("Selector", selector.getClassName());
        geneticParameters.put("Replacer", replacer.getClassName());

        JSONObject items = new JSONObject();
        items.put("Helmet", itemPool.getHelmets());
        items.put("Chestplate", itemPool.getChestplates());
        items.put("Gauntlets", itemPool.getGauntlets());
        items.put("Weapons", itemPool.getWeapons());
        items.put("Boots", itemPool.getBoots());

        JSONObject characterDetails = new JSONObject();
        characterDetails.put("Class", characterClass);
        characterDetails.put("Items", items);

        JSONObject data = new JSONObject();
        data.put("Genetic Parameters", geneticParameters);
        data.put("Character details", characterDetails);

        try(FileWriter file = new FileWriter("character.json")){
            file.write(data.toJSONString());
            file.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
