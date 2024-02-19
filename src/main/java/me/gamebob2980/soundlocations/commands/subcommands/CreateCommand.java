package me.gamebob2980.soundlocations.commands.subcommands;

import me.gamebob2980.soundlocations.SoundLocations;
import me.gamebob2980.soundlocations.commands.SubCommand;
import me.gamebob2980.soundlocations.utils.MessageUtil;
import me.gamebob2980.soundlocations.utils.Permissions;
import me.gamebob2980.soundlocations.utils.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateCommand extends SubCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create a new sound";
    }

    @Override
    public String getSyntax() {
        return "/sounds create <name> <sound> <volume> <pitch> <duration>";
    }

    @Override
    public void perform(Player p, String[] args) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        if(p.hasPermission(Permissions.createPermission)) {

            Location loc = p.getLocation();

            if (args.length < 6) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Incorrect usage!"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Usage: " + getSyntax()));
            } else if (args.length == 6) {
                SoundUtil.addSound(args[1], (args[2]).toUpperCase(), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), loc, p);
            }
        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "You do not have permission to run this command."));
        }
    }
}
