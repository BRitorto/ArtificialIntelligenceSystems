package ar.edu.itba.sia.Utils;

import ar.edu.itba.sia.Game.GameCharacter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diversity {
     double minAttack;
     double minDefense;
     double maxAttack;
     double maxDefense;
     private Map<Long,int[]> map;


    public Diversity() {
        this.minAttack = Double.MAX_VALUE;
        this.minDefense = Double.MAX_VALUE;
        map = new HashMap<>();
    }

    public void calculateAttackDefense(GameCharacter ch) {
        double attack=ch.getAttack();
        double defense=ch.getDefense();

        if(minAttack>attack) {
            minAttack = attack;
        }
        if(maxAttack<attack){
            maxAttack=attack;
        }
        if(minDefense>defense){
            minDefense=defense;
        }
        if(maxDefense<defense) {
            maxDefense = defense;
        }

    }

    public int[] calculateDiversity(List<GameCharacter> population, long generation) {
        initializeGenerationDiversity(generation);

        this.minAttack = Double.MAX_VALUE;
        this.minDefense = Double.MAX_VALUE;
        this.maxAttack = 0;
        this.maxDefense = 0;

        for(GameCharacter character:population) {
            calculateAttackDefense(character);
        }
        for(GameCharacter character:population) {
            double attack = character.getAttack();
            double defense = character.getDefense();
            if(attack >= 0.8*(maxAttack-minAttack)+minAttack) {
                if(defense >= 0.8*(maxDefense-minDefense)+minDefense){ //goodAttackAndDefense
                    map.get(generation)[2]++;
                }
                else //goodAttack
                    map.get(generation)[0]++;
            }
            else if(defense >= 0.8*(maxDefense-minDefense)+minDefense){ //goodDefense
                map.get(generation)[1]++;
            }
            else
                map.get(generation)[3]++;
        }

        return map.get(generation);
    }

    private void initializeGenerationDiversity(long generation) {
        int[] groups  = {0,0,0,0}; // goodAttack, goodDefense, goodAttackAndDefense, rest
        map.put(generation,groups);
    }

    public double getMinAttack() {
        return minAttack;
    }

    public double getMinDefense() {
        return minDefense;
    }

    public double getMaxAttack() {
        return maxAttack;
    }

    public double getMaxDefense() {
        return maxDefense;
    }
}
