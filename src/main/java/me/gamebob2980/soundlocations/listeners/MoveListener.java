package me.gamebob2980.soundlocations.listeners;

import me.gamebob2980.soundlocations.utils.SoundUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){

        Player p = e.getPlayer();
        Location playerLoc = p.getLocation();

        SoundUtil.findSound(p, playerLoc);
    }
}
