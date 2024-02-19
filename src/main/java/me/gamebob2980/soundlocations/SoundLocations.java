package me.gamebob2980.soundlocations;

import me.gamebob2980.soundlocations.commands.CommandManager;
import me.gamebob2980.soundlocations.commands.subcommands.ReloadCommand;
import me.gamebob2980.soundlocations.files.Storage;
import me.gamebob2980.soundlocations.listeners.MenuListener;
import me.gamebob2980.soundlocations.listeners.MoveListener;
import me.gamebob2980.soundlocations.menumanager.PlayerMenuUtility;
import me.gamebob2980.soundlocations.utils.MessageUtil;
import me.gamebob2980.soundlocations.utils.SoundUtil;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class SoundLocations extends JavaPlugin {

    private static SoundLocations plugin;
    public ArrayList<Player> playersListeningToSounds = new ArrayList<>();
    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("Enabled!");

        plugin = this;

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);

        getCommand("sounds").setExecutor(new CommandManager());

        SoundUtil soundUtil = new SoundUtil(this);
        MessageUtil messageUtil = new MessageUtil(this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Storage.setup();
        Storage.get().options().copyDefaults();
        if(!Storage.get().isConfigurationSection("savedSounds")){
            Storage.get().createSection("savedSounds");
        }
        Storage.save();
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p){
        PlayerMenuUtility playerMenuUtility;

        if(playerMenuUtilityMap.containsKey(p)){
            return playerMenuUtilityMap.get(p);
        }else{

            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        }
    }

    public static SoundLocations getPlugin(){
        return plugin;
    }
}
