package me.gamebob2980.soundlocations.listeners;

import me.gamebob2980.soundlocations.menumanager.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){

        if(e.getClickedInventory() != null && e.getCurrentItem() != null) {
            InventoryHolder holder = e.getClickedInventory().getHolder();

            if (holder instanceof Menu) {

                e.setCancelled(true);

                Menu menu = (Menu) holder;
                menu.handleMenu(e);
            }
        }
    }
}
