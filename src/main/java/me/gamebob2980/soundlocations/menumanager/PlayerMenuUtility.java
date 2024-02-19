package me.gamebob2980.soundlocations.menumanager;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private Player owner;
    private String section;

    public PlayerMenuUtility(Player owner){
        this.owner = owner;
    }

    public Player getOwner(){
        return owner;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }
}
