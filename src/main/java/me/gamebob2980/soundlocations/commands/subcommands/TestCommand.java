package me.gamebob2980.soundlocations.commands.subcommands;

import me.gamebob2980.soundlocations.SoundLocations;
import me.gamebob2980.soundlocations.commands.SubCommand;
import me.gamebob2980.soundlocations.utils.MessageUtil;
import me.gamebob2980.soundlocations.utils.Permissions;
import me.gamebob2980.soundlocations.utils.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TestCommand extends SubCommand {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getDescription() {
        return "Test a sound";
    }

    @Override
    public String getSyntax() {
        return "/sounds test <sound> <volume> <pitch>";
    }

    @Override
    public void perform(Player p, String[] args) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        if(p.hasPermission(Permissions.testPermission)) {

            if (args.length < 4) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Incorrect usage!"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Usage: " + getSyntax()));
            } else if (args.length == 4) {
                SoundUtil.testSound(p, args[1].toUpperCase(), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            }
        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "You do not have permission to run this command."));
        }
    }
}
