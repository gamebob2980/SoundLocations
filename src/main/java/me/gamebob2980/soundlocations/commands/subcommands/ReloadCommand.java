package me.gamebob2980.soundlocations.commands.subcommands;

import me.gamebob2980.soundlocations.SoundLocations;
import me.gamebob2980.soundlocations.commands.SubCommand;
import me.gamebob2980.soundlocations.files.Storage;
import me.gamebob2980.soundlocations.utils.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload the plugins config files";
    }

    @Override
    public String getSyntax() {
        return "/sounds reload";
    }

    @Override
    public void perform(Player p, String[] args) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        if(p.hasPermission(Permissions.reloadPermission)){

            SoundLocations.getPlugin().reloadConfig();
            Storage.reload();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Config reloaded!"));
        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "You do not have permission to run this command."));
        }
    }
}
