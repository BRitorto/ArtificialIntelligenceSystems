package ar.edu.itba.sia.Engine;

import ar.edu.itba.sia.Game.Item;

import java.util.List;

public class Items {
    private List<Item> helmets;
    private List<Item> weapons;
    private List<Item> chestplates;
    private List<Item> gauntlets;
    private List<Item> boots;

    public List<Item> getGauntlets() {
        return gauntlets;
    }

    public void setGauntlets(List<Item> gauntlets) {
        this.gauntlets = gauntlets;
    }

    public List<Item> getHelmets() {
        return helmets;
    }

    public void setHelmets(List<Item> helmets) {
        this.helmets = helmets;
    }

    public List<Item> getBoots() {
        return boots;
    }

    public void setBoots(List<Item> boots) {
        this.boots = boots;
    }

    public List<Item> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Item> weapons) {
        this.weapons = weapons;
    }

    public List<Item> getChestplates() {
        return chestplates;
    }

    public void setChestplates(List<Item> chestplates) {
        this.chestplates = chestplates;
    }
}
