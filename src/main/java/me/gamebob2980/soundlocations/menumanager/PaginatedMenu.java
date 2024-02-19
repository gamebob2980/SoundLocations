package me.gamebob2980.soundlocations.menumanager;

import me.gamebob2980.soundlocations.SoundLocations;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class PaginatedMenu extends Menu {

    protected int page = 0;
    protected int maxItemsPerPage = 45;
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility){
        super(playerMenuUtility);
    }

    public void addMenuItems(){

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        ItemStack back = new ItemStack(Material.ARROW, 1);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', primary + "Back"));
        back.setItemMeta(backMeta);

        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(closeMeta);

        ItemStack next = new ItemStack(Material.ARROW, 1);
        ItemMeta nextMeta = next.getItemMeta();
        nextMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', primary + "Next"));
        next.setItemMeta(nextMeta);

        inventory.setItem(47, back);
        inventory.setItem(49, close);
        inventory.setItem(51, next);
    }
}
