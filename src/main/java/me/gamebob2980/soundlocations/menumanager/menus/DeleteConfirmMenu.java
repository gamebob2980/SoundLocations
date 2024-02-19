package me.gamebob2980.soundlocations.menumanager.menus;

import me.gamebob2980.soundlocations.menumanager.Menu;
import me.gamebob2980.soundlocations.menumanager.PlayerMenuUtility;
import me.gamebob2980.soundlocations.utils.SoundUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeleteConfirmMenu extends Menu {

    public DeleteConfirmMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Delete Sound";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        if(e.getCurrentItem().getType().equals(Material.EMERALD)){

            e.getWhoClicked().closeInventory();
            SoundUtil.deleteSound(playerMenuUtility.getSection(), (Player) e.getWhoClicked());
        }else if(e.getCurrentItem().getType().equals(Material.BARRIER)){

            new SoundsMenu(playerMenuUtility).open();
        }
    }

    @Override
    public void setMenuItems() {

        ItemStack confirm = new ItemStack(Material.EMERALD, 1);
        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN + "Confirm");
        confirm.setItemMeta(confirmMeta);

        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.RED + "Cancel");
        cancel.setItemMeta(cancelMeta);

        inventory.setItem(11, confirm);
        inventory.setItem(15, cancel);
    }
}
