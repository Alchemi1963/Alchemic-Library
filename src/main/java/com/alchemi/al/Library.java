package com.alchemi.al;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.configurations.SexyConfiguration;
import com.alchemi.al.objects.meta.BaseMeta;

public class Library extends JavaPlugin{
	public static Library instance;
	
	public SexyConfiguration sc;
	
	public void onEnable() {
		System.out.println("Hello Stonehenge!");
		instance = this;
	}
	
	public void onDisable(){
		System.out.println("I don't wanna go...");
		saveConfig();
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
	 * Test if a player has a certain meta value.
	 * 
	 * @param player	The player to test
	 * @param metaKey	The meta key to test for
	 * @param clazz		The class of the key
	 * @return			true or false
	 */
	public static boolean hasMeta(Player player, Class<? extends BaseMeta> clazz) {
		
		String metaKey = "";
		try {
			metaKey = clazz.getConstructor().newInstance().name();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {}
		
		if (!player.hasMetadata(metaKey)) return false;
		
		for (MetadataValue meta : player.getMetadata(metaKey)) {
			if (clazz.isInstance(meta)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get a meta value from a player
	 * 
	 * @param player	The player to get the meta from
	 * @param metaKey	The meta key to get
	 * @param clazz		The class the key belongs to
	 * @return			{@link MetadataValue} of the key, {@code null} of not found
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static MetadataValue getMeta(Player player, Class<? extends BaseMeta> clazz) {
		if (!hasMeta(player, clazz)) return null;
		
		String metaKey = "";
		try {
			metaKey = clazz.getConstructor().newInstance().name();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {}
		
		for (MetadataValue meta : player.getMetadata(metaKey)) {
			if (clazz.isInstance(meta)) {
				return meta;
			}
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
}
