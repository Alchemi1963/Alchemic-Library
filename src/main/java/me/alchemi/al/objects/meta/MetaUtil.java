package me.alchemi.al.objects.meta;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;

public class MetaUtil {

	/**
	 * 
	 * @param <T>
	 * @param metadatable the {@link Metadatable} object
	 * @param key the meta key
	 * @param clazz the {@link Class} from the metadata, should implement {@link MetadataValue}
	 * @return the MetadataValue, cast to the correct class
	 */
	public static <T> T getMeta(Metadatable metadatable, String key, Class<T> clazz) {
		
		if (!MetaUtil.hasMeta(metadatable, key, (Class<? extends MetadataValue>) clazz)) return null;
		
		for (MetadataValue meta : metadatable.getMetadata(key)) {
			if (clazz.isInstance(meta)) {
				return (T) meta;
			}
		}
		return null;
	}

	/**
	 * Test if a player has a certain meta value.
	 * 
	 * @param metadatable the {@link Metadatable} object
	 * @param key the meta key
	 * @param clazz the {@link Class} from the metadata
	 * @return
	 */
	public static boolean hasMeta(Metadatable metadatable, String key, Class<? extends MetadataValue> clazz) {
		
		if (!metadatable.hasMetadata(key)) return false;
		
		for (MetadataValue meta : metadatable.getMetadata(key)) {
			if (clazz.isInstance(meta)) {
				return true;
			}
		}
		return false;
	}

	
}
