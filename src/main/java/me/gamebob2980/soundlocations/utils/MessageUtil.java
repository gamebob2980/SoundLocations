package me.gamebob2980.soundlocations.utils;

import me.gamebob2980.soundlocations.SoundLocations;

public class MessageUtil {

    static SoundLocations plugin;

    public MessageUtil(SoundLocations plugin) {
        this.plugin = plugin;
    }

    public static String replace(String message, String name, String sound, String x, String y, String z){

        return message.replace("{name}", name).replace("{sound}", sound).replace("{X}", x).replace("{Y}", y).replace("{Z}", z);
    }
}
