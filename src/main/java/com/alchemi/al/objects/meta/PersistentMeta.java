package com.alchemi.al.objects.meta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;

import com.alchemi.al.Library;

public class PersistentMeta implements Listener{

	protected static Map<UUID, FileConfiguration> persistentMetas = new HashMap<UUID, FileConfiguration>();

	protected static List<UUID> changeUUIDs = new ArrayList<UUID>();
	
	protected static final File metaFiles = new File(Library.instance.getDataFolder(), "metas");
	
	protected static String getName(Player player) {
		return player.getUniqueId().toString() + ".yml";
	}
	
	protected static File getFile(Player player) {
		return new File(metaFiles, getName(player));
	}
	
	public static void enable() {
		if (!metaFiles.exists()) metaFiles.mkdirs();
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			initializePlayer(player);
		}
		
		Bukkit.getPluginManager().registerEvents(new PersistentMeta(), Library.instance);
	}
	
	protected static void initializePlayer(Player player) {
		if (getFile(player).exists()) {
			FileConfiguration file = YamlConfiguration.loadConfiguration(getFile(player));
			persistentMetas.put(player.getUniqueId(), file);
			
			List<Map<String, Object>> desMaps = new ArrayList<Map<String,Object>>();
			for (String key : file.getValues(true).keySet()) {
				if (key.endsWith("owner")) {
					String k = key.substring(0, key.lastIndexOf("."));
					Map<String, Object> m = file.getConfigurationSection(k).getValues(true);
					m.put("==", k);
					desMaps.add(m);
					continue;
				}
			}
			for (Map<String, Object> m : desMaps) {
				player.setMetadata((String) m.get("=="), BaseMeta.deserialize(m));
			}
		}
	}
	
	public static void setMeta(Player player, BaseMeta metaValue) {
		
		player.setMetadata(metaValue.getClass().getName(), metaValue);
		
		FileConfiguration file;
		if (persistentMetas.containsKey(player.getUniqueId())) {
			file = persistentMetas.get(player.getUniqueId());
		} else if (getFile(player).exists()) {
			file = YamlConfiguration.loadConfiguration(getFile(player));
		} else {
			file = new YamlConfiguration();
		}
		
		file.set(metaValue.getClass().getName(), metaValue.serialize());
		
		if (!changeUUIDs.contains(player.getUniqueId())) changeUUIDs.add(player.getUniqueId());
		persistentMetas.put(player.getUniqueId(), file);
		
	}
	
	public static void save(Player player) {
		if (persistentMetas.containsKey(player.getUniqueId()) && changeUUIDs.contains(player.getUniqueId())) {
			FileConfiguration file = persistentMetas.get(player.getUniqueId());
			try {
				file.save(getFile(player));
				changeUUIDs.remove(player.getUniqueId());
			} catch (IOException e) {}
		} 
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
		
		if (!player.hasMetadata(clazz.getName())) return false;
		
		for (MetadataValue meta : player.getMetadata(clazz.getName())) {
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
	public static MetadataValue getMeta(Player player, Class<? extends BaseMeta> clazz) {
		if (!hasMeta(player, clazz)) return null;
		
		for (MetadataValue meta : player.getMetadata(clazz.getName())) {
			if (clazz.isInstance(meta)) {
				return meta;
			}
		}
		return null;
	}
	
	@EventHandler
	public static void onLogin(PlayerLoginEvent e) {
		initializePlayer(e.getPlayer());
	}
	
	@EventHandler
	public static void onLogout(PlayerQuitEvent e) {
		save(e.getPlayer());
	}
}
