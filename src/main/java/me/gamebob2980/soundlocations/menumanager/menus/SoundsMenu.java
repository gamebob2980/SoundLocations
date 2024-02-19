package me.gamebob2980.soundlocations.menumanager.menus;

import me.gamebob2980.soundlocations.SoundLocations;
import me.gamebob2980.soundlocations.files.Storage;
import me.gamebob2980.soundlocations.menumanager.PaginatedMenu;
import me.gamebob2980.soundlocations.menumanager.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
public class SoundsMenu extends PaginatedMenu {

    public SoundsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Sounds";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        ArrayList<String> sounds = new ArrayList<>();

        ConfigurationSection section = Storage.get().getConfigurationSection("savedSounds");
        if(section != null){
            for(String sections : section.getKeys(false)){
                sounds.add(sections);
            }
        }

        if(e.getCurrentItem().getType().equals(Material.NOTE_BLOCK)){
            playerMenuUtility.setSection(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));

            new DeleteConfirmMenu(playerMenuUtility).open();
        }else if(e.getCurrentItem().getType().equals(Material.BARRIER)){
            e.getWhoClicked().closeInventory();
        }else if(e.getCurrentItem().getType().equals(Material.ARROW)){
            if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Back")){
                if(page == 0){
                    e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "You are already on the first page."));
                }else{
                    page = page -1;
                    super.open();
                }
            }else if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Next")){
                if(!((index + 1) >= sounds.size())){
                    page = page + 1;
                    super.open();
                }else{
                    e.getWhoClicked().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + primary + "You are on the last page."));
                }
            }
        }
    }

    @Override
    public void setMenuItems() {

        String prefix = SoundLocations.getPlugin().getConfig().getString("prefix");
        String primary = SoundLocations.getPlugin().getConfig().getString("primary-colour");
        String secondary = SoundLocations.getPlugin().getConfig().getString("secondary-colour");

        addMenuItems();

        ArrayList<String> sounds = new ArrayList<>();

        ConfigurationSection section = Storage.get().getConfigurationSection("savedSounds");
        if(section != null){
            for(String sections : section.getKeys(false)){
                sounds.add(sections);
            }
        }

        if(sounds != null){
            for(int i = 0; i < super.maxItemsPerPage; i++){
                index = super.maxItemsPerPage * page + i;
                if(index >= sounds.size()) break;
                if(sounds.get(index) != null){

                    ItemStack soundItem = new ItemStack(Material.NOTE_BLOCK);
                    ItemMeta soundMeta = soundItem.getItemMeta();
                    soundMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', primary + "&l" + sounds.get(index)));
                    ArrayList<String> lore = new ArrayList<>();
                    ConfigurationSection sound = Storage.get().getConfigurationSection("savedSounds." + sounds.get(index));

                    double x = Math.round(sound.getDouble("x"));
                    double y = Math.round(sound.getDouble("y"));
                    double z = Math.round(sound.getDouble("z"));

                    lore.add(ChatColor.translateAlternateColorCodes('&', secondary + "Sound: " + sound.getString("sound")));
                    lore.add(ChatColor.translateAlternateColorCodes('&', secondary + "Volume: " + sound.getInt("volume")));
                    lore.add(ChatColor.translateAlternateColorCodes('&', secondary + "Pitch: " + sound.getInt("pitch")));
                    lore.add(ChatColor.translateAlternateColorCodes('&', secondary + "Duration: " + sound.getInt("duration") + "s"));
                    lore.add(ChatColor.translateAlternateColorCodes('&', secondary + "Location: " + x + " " + y + " " + z));
                    soundMeta.setLore(lore);
                    soundItem.setItemMeta(soundMeta);

                    inventory.addItem(soundItem);
                }
            }
        }
    }
}
