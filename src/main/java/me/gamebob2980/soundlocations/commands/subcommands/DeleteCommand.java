package me.gamebob2980.soundlocations.commands.subcommands;

import me.gamebob2980.soundlocations.SoundLocations;
import me.gamebob2980.soundlocations.commands.SubCommand;
import me.gamebob2980.soundlocations.utils.MessageUtil;
import me.gamebob2980.soundlocations.utils.Permissions;
import me.gamebob2980.soundlocations.utils.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DeleteCommand extends SubCommand {

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Delete a sound";
    }

    @Override
    public String getSyntax() {
        return "/sounds delete <sound> confirm";
    }

    @Override
    public void perform(Player p, String[] args) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        if(p.hasPermission(Permissions.deletePermission)) {

            if (args.length < 2) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Incorrect usage!"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Usage: " + getSyntax()));
            } else if (args.length == 2) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Please confirm that you would like to delete this sound."));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "Type: /sounds delete " + args[1] + " confirm"));
            } else if (args.length == 3) {
                SoundUtil.deleteSound(args[1], p);
            }
        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "You do not have permission to run this command."));
        }
    }
}
