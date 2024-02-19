package me.gamebob2980.soundlocations.commands.subcommands;

import me.gamebob2980.soundlocations.SoundLocations;
import me.gamebob2980.soundlocations.commands.SubCommand;
import me.gamebob2980.soundlocations.menumanager.menus.SoundsMenu;
import me.gamebob2980.soundlocations.utils.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MenuCommand extends SubCommand {

    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public String getDescription() {
        return "Open the menu of sounds";
    }

    @Override
    public String getSyntax() {
        return "/sounds menu";
    }

    @Override
    public void perform(Player p, String[] args) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        if (p.hasPermission(Permissions.menuPermission)) {

            new SoundsMenu(SoundLocations.getPlayerMenuUtility(p)).open();
        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "You do not have permission to run this command."));
        }
    }
}
