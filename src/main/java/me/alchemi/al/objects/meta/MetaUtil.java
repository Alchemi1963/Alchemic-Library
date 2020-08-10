package me.alchemi.al.objects.meta;

import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class MetaUtil {

	/**
	 * 
	 * @param <T>
	 * @param player the {@link Player}
	 * @param key the meta key
	 * @param clazz the {@link Class} from the metadata, should implement {@link MetadataValue}
	 * @return the MetadataValue, cast to the correct class
	 */
	public static <T> T getMeta(Player player, String key, Class<T> clazz) {
		
		if (!MetaUtil.hasMeta(player, key, (Class<? extends MetadataValue>) clazz)) return null;
		
		for (MetadataValue meta : player.getMetadata(key)) {
			if (clazz.isInstance(meta)) {
				return (T) meta;
			}
		}
		return null;
	}

	/**
	 * Test if a player has a certain meta value.
	 * 
	 * @param player the {@link Player}
	 * @param key the meta key
	 * @param clazz the {@link Class} from the metadata
	 * @return
	 */
	public static boolean hasMeta(Player player, String key, Class<? extends MetadataValue> clazz) {
		
		if (!player.hasMetadata(key)) return false;
		
		for (MetadataValue meta : player.getMetadata(key)) {
			if (clazz.isInstance(meta)) {
				return true;
			}
		}
		return false;
	}

	
}
