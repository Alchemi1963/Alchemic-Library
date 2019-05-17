package com.alchemi.al;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;

import com.alchemi.al.configurations.Messenger;
import com.alchemi.al.configurations.SexyConfiguration;
import com.alchemi.al.objects.base.PluginBase;
import com.alchemi.al.objects.handling.UpdateChecker;
import com.alchemi.al.objects.meta.PersistentMeta;

public class Library extends PluginBase implements Listener {
	
	public static Library instance;
	
	public SexyConfiguration sc;
	
	public UpdateChecker uc;
	
	@Override
	public void onEnable() {
		instance = this;
		
		this.SPIGOT_ID = 62777;
		this.setMessenger(new Messenger(this));
		
		uc = new UpdateChecker(this);
		
		PersistentMeta.enable();
		
		Bukkit.getPluginManager().registerEvents(this, this);
		
		getLogger().info(Messenger.cc("&8Hello Stonehenge!"));
	}
	
	public void onDisable(){
	
		getLogger().info(Messenger.cc("&6Saving player metadata..."));
		for (Player player : Bukkit.getOnlinePlayers()) {
			PersistentMeta.save(player);
		}
		
		getLogger().info(Messenger.cc("&7I don't wanna go..."));
	}
	
	/**
	 * Gets an OfflinePlayer by name.
	 * isn't deprecated, like {@linkplain Bukkit.getOfflinePlayer(String name)}
	 * 
	 * @param name 	The name of the player
	 * @return		The OfflinePlayer
	 */
	public static OfflinePlayer getOfflinePlayer(String name) {
		for (OfflinePlayer offP : Bukkit.getOfflinePlayers()) {
			if (offP.getName().equals(name)) return offP;
		}
		return null;
	}
	
	/**
	 * Give an itemstack to a player.
	 * 
	 * @param item		The itemstack to give.
	 * @param player	The player to give it to.
	 * @see {@link ItemStack}, {@link Player}
	 */
	public static void giveItemStack(ItemStack item, Player player) {
		if (item.getAmount() > item.getMaxStackSize()) { 
			ItemStack item2 = item.clone();
			item2.setAmount(item.getAmount() - item.getMaxStackSize());
			giveItemStack(item2, player);
			item.setAmount(item.getMaxStackSize());
		}
		
		if (player.getInventory().firstEmpty() == -1) {
			player.getWorld().dropItem(player.getLocation(), item);
		} else {
			player.getPlayer().getInventory().addItem(item);
		}
	}
	
	/**
	 * Test if a string is an integer.
	 * 
	 * @param input the integer to test
	 * @return Wether the string is an integer or not.
	 */
	public static boolean testIfInt(String input) {
		try {
			Integer.valueOf(input);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Test if a string is a number.
	 * 
	 * @param input the string to test.
	 * @return Wether the string is a number or not.
	 */
	public static boolean testIfNumber(String input) {
		try {
			Float.valueOf(input);
			return true;
		} catch(NumberFormatException e) {
			try {
				Double.valueOf(input);
				return true;
			} catch (NumberFormatException ex) {
				try {
					Integer.valueOf(input);
					return true;
				} catch (NumberFormatException exc) {
					try {
						Long.valueOf(input);
						return true;
					} catch (NumberFormatException exce) {
						return false;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("al.forcecheckupdate")) {
			uc.check();
		}
	}
}
