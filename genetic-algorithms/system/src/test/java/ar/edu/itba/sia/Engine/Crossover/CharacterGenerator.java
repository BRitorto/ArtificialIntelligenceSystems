package ar.edu.itba.sia.Engine.Crossover;

import ar.edu.itba.sia.Game.GameCharacter;
import ar.edu.itba.sia.Game.Item;
import ar.edu.itba.sia.Game.Profession;

public class CharacterGenerator {

    public static GameCharacter createCharacter(Profession prof, Integer i){
        Object[] items = new Object[6];
        Integer value;
        for(int j = 0; j< 5; j++){
            value = i+ j; // This value was chosen empirically but could be any other expression of i and j
            items[j] = new Item(value.toString(), value, value, value, value, value);
        }

        items[5] = 1.4;
        return new GameCharacter(prof, items);
    }
}
