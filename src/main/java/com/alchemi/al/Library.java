package com.alchemi.al;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.sexyconfs.SexyConfiguration;

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
	public static boolean hasMeta(Player player, String metaKey, Class<? extends MetadataValueAdapter> clazz) {
		
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
	 */
	public static MetadataValue getMeta(Player player, String metaKey, Class<? extends MetadataValueAdapter> clazz) {
		if (!hasMeta(player, metaKey, clazz)) return null;
		
		for (MetadataValue meta : player.getMetadata(metaKey)) {
			if (clazz.isInstance(meta)) {
				return meta;
			}
		}
		return null;
	}
}
