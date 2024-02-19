package me.gamebob2980.soundlocations.utils;

import me.gamebob2980.soundlocations.SoundLocations;
import me.gamebob2980.soundlocations.files.Storage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SoundUtil {

    static SoundLocations plugin;

    public SoundUtil(SoundLocations plugin) {
        this.plugin = plugin;
    }

    public static void addSound(String name, String sound, float volume, float pitch, int duration, Location loc, Player p) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        String x = String.valueOf(Math.round(loc.getX()));
        String y = String.valueOf(Math.round(loc.getY()));
        String z = String.valueOf(Math.round(loc.getZ()));

        if (!(Storage.get().isConfigurationSection("savedSounds." + name)))  {
            Storage.get().createSection("savedSounds." + name);
            Storage.get().set("savedSounds." + name + ".sound", sound);
            Storage.get().set("savedSounds." + name + ".volume", volume);
            Storage.get().set("savedSounds." + name + ".pitch", pitch);
            Storage.get().set("savedSounds." + name + ".duration", duration);
            Storage.get().set("savedSounds." + name + ".x", loc.getX());
            Storage.get().set("savedSounds." + name + ".y", loc.getY());
            Storage.get().set("savedSounds." + name + ".z", loc.getZ());
            Storage.save();

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + MessageUtil.replace(plugin.getConfig().getString("sound-created"), name, sound, x, y, z)));
        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + MessageUtil.replace(plugin.getConfig().getString("sound-already-exists"), name, sound, x, y, z)));
        }
    }

    public static void findSound(Player p, Location playerLoc){

        ConfigurationSection locations = Storage.get().getConfigurationSection("savedSounds");

        if(locations != null){
            for(String locationName : locations.getKeys(false)) {
                ConfigurationSection locationSection = locations.getConfigurationSection(locationName);

                if (locationSection != null) {
                    double x = locationSection.getDouble("x");
                    double y = locationSection.getDouble("y");
                    double z = locationSection.getDouble("z");
                    double radius = plugin.getConfig().getDouble("radius");

                    Location loc = new Location(playerLoc.getWorld(), x, y, z);

                    if (isWithinDistance(playerLoc, loc, radius)) {
                        playSound(p, locationSection);
                    }
                }
            }
        }
    }

    public static void playSound(Player p, ConfigurationSection sound) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        if (!(plugin.playersListeningToSounds.contains(p))) {

            double x = sound.getDouble("x");
            double y = sound.getDouble("y");
            double z = sound.getDouble("z");
            int duration = sound.getInt("duration");
            Location loc = new Location(p.getWorld(), x, y, z);

            try {
                p.playSound(loc, Sound.valueOf(sound.getString("sound")), sound.getInt("volume"), sound.getInt("pitch"));
            } catch (IllegalArgumentException e) {
                try {
                    p.playSound(loc, sound.getString("sound"), sound.getInt("volume"), sound.getInt("pitch"));
                } catch (IllegalArgumentException a) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + MessageUtil.replace(plugin.getConfig().getString("sound-does-not-exist"), sound.getString("name"), sound.getString("sound"), "", "", "")));
                }
            }

            plugin.playersListeningToSounds.add(p);

            new BukkitRunnable() {
                @Override
                public void run() {
                    plugin.playersListeningToSounds.remove(p);
                }
            }.runTaskLater(plugin, 20 * duration);
        }
    }

    public static void deleteSound(String name, Player p){

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        if(Storage.get().isConfigurationSection("savedSounds." + name)){
            Storage.get().set("savedSounds." + name, null);
            Storage.save();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + MessageUtil.replace(plugin.getConfig().getString("deleted-sound"), name, "", "", "", "")));
        }else{
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + MessageUtil.replace(plugin.getConfig().getString("sound-does-not-exist"), name, "", "", "", "")));
        }
    }

    public static void testSound(Player p, String sound, float volume, float pitch){

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        try{
            p.playSound(p.getLocation(), Sound.valueOf(sound), volume, pitch);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + MessageUtil.replace(plugin.getConfig().getString("playing-sound"), "", sound, "", "", "")));
        }catch (IllegalArgumentException e) {
            try{
                p.playSound(p.getLocation(), sound, volume, pitch);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + MessageUtil.replace(plugin.getConfig().getString("playing-sound"), "", sound, "", "", "")));
            }catch (IllegalArgumentException a) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + MessageUtil.replace(plugin.getConfig().getString("unknown-sound"), "", sound, "", "", "")));
            }
        }
    }

    static boolean isWithinDistance(Location playerLoc, Location loc, double distance){
        return playerLoc.distance(loc) <= distance;
    }
}
