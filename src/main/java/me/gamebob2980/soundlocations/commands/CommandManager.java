package me.gamebob2980.soundlocations.commands;

import me.gamebob2980.soundlocations.SoundLocations;
import me.gamebob2980.soundlocations.commands.subcommands.*;
import me.gamebob2980.soundlocations.utils.MessageUtil;
import me.gamebob2980.soundlocations.utils.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements TabExecutor {

    private ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager(){
        subcommands.add(new CreateCommand());
        subcommands.add(new MenuCommand());
        subcommands.add(new TestCommand());
        subcommands.add(new DeleteCommand());
        subcommands.add(new ReloadCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        if(sender instanceof Player){

            Player p = (Player) sender;

            if(p.hasPermission(Permissions.usePermission)) {
                if (args.length > 0) {
                    for (int i = 0; i < getSubcommands().size(); i++) {
                        if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                            getSubcommands().get(i).perform(p, args);
                        }
                    }
                } else if (args.length == 0) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', primary + "----------------------------------"));
                    for (int i = 0; i < getSubcommands().size(); i++) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', secondary + getSubcommands().get(i).getSyntax()));
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', primary + "----------------------------------"));
                }
            }else{
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "You do not have permission to run this command."));
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubcommands(){
        return subcommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 1){

            ArrayList<String> subcommandArgs = new ArrayList<>();
            for(int i = 0; i < getSubcommands().size(); i++){
                subcommandArgs.add(getSubcommands().get(i).getName());
            }
            return subcommandArgs;
        }
        return null;
    }
}
