package me.alchemi.al.objects.meta;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTEntity;

public class PersistentMeta {
	
	public void enable(Plugin plugin) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			initializePlayer(player, plugin);
		}
	}
	
	/**
	 * Initialize the player for the given plugin
	 * 
	 * @param player
	 * @param plugin
	 */
	public static void initializePlayer(Player player, Plugin plugin) {
		
		NBTEntity nbtp = new NBTEntity(player);
		NBTCompound compound = nbtp.getPersistentDataContainer();
		if (compound.hasKey("metadata." + plugin.getName())) {
			NBTCompound pCompound = compound.getCompound("metadata." + plugin.getName());
			for (String key : pCompound.getKeys()) {
				Type type = pCompound.getObject(key + ".type", Type.class);
				Object value;
				
				switch(type) {
				case BOOLEAN:
					value = pCompound.getBoolean(key + ".value");
					break;
				case BYTE:
					value = pCompound.getByte(key + ".value");
					break;
				case BYTE_ARRAY:
					value = pCompound.getByteArray(key + ".value");
					break;
				case DOUBLE:
					value = pCompound.getDouble(key + ".value");
					break;
				case FLOAT:
					value = pCompound.getFloat(key + ".value");
					break;
				case INT:
					value = pCompound.getInteger(key + ".value");
					break;
				case INT_ARRAY:
					value = pCompound.getIntArray(key + ".value");
					break;
				case ITEMSTACK:
					value = pCompound.getItemStack(key + ".value");
					break;
				case LONG:
					value = pCompound.getFloat(key + ".value");
					break;
				case OBJECT:
					try {
						value = pCompound.getObject(key + ".value", Class.forName(pCompound.getString(key + ".valueClass")));
					} catch (ClassNotFoundException e) {
						plugin.getLogger().warning("Could not get metadata value " + key + " for " + player.getName());
						plugin.getLogger().warning(e.getMessage());
						continue;
					}
					break;
				case SHORT:
					value = pCompound.getShort(key + ".value");
					break;
				case STRING:
					value = pCompound.getString(key + ".value");
					break;
				default:
					value = null;
					break;
				}
				
				player.setMetadata(key, new MetadataValueAdapter(plugin) {

					private Object valueMeta = value;
					
					@Override
					public @Nullable Object value() {
						return valueMeta;
					}
					
					@Override
					public void invalidate() {
						valueMeta = null;
					}
				});
				
			}
		}		
	}
	
	/**
	 * Set a meta value
	 * 
	 * @param player the {@link Player}
	 * @param key the meta key
	 * @param metaValue the {@link MetadataValue}
	 */
	public static void setMeta(Player player, String key, MetadataValue metaValue) {
		
		NBTEntity nbtp = new NBTEntity(player);
		NBTCompound compound = nbtp.getPersistentDataContainer();
		NBTCompound metaCompound = compound.addCompound("metadata." + metaValue.getOwningPlugin().getName() + "." + key);
		if (metaValue.value() instanceof Boolean) {
			metaCompound.setObject("type", Type.BOOLEAN);
			metaCompound.setBoolean("value", metaValue.asBoolean());
		} else if (metaValue.value() instanceof Byte) {
			metaCompound.setObject("type", Type.BYTE);
			metaCompound.setByte("value", metaValue.asByte());
		} else if (metaValue.value() instanceof byte[]) {
			metaCompound.setObject("type", Type.BYTE_ARRAY);
			metaCompound.setByteArray("value", (byte[])metaValue.value());
		} else if (metaValue.value() instanceof Double) {
			metaCompound.setObject("type", Type.DOUBLE);
			metaCompound.setDouble("value", metaValue.asDouble());
		} else if (metaValue.value() instanceof Float) {
			metaCompound.setObject("type", Type.FLOAT);
			metaCompound.setFloat("value", metaValue.asFloat());
		} else if (metaValue.value() instanceof int[]) {
			metaCompound.setObject("type", Type.INT_ARRAY);
			metaCompound.setIntArray("value", (int[]) metaValue.value());
		} else if (metaValue.value() instanceof Integer) {
			metaCompound.setObject("type", Type.INT);
			metaCompound.setInteger("value", metaValue.asInt());
		} else if (metaValue.value() instanceof ItemStack) {
			metaCompound.setObject("type", Type.ITEMSTACK);
			metaCompound.setItemStack("value", (ItemStack) metaValue.value());
		} else if (metaValue.value() instanceof Long) {
			metaCompound.setObject("type", Type.LONG);
			metaCompound.setLong("value", metaValue.asLong());
		} else if (metaValue.value() instanceof Short) {
			metaCompound.setObject("type", Type.SHORT);
			metaCompound.setShort("value", metaValue.asShort());
		} else if (metaValue.value() instanceof String) {
			metaCompound.setObject("type", Type.STRING);
			metaCompound.setObject("value", metaValue.asString());
		} else if (metaValue.value() instanceof Serializable) {
			metaCompound.setObject("type", Type.OBJECT);
			metaCompound.setObject("value", metaValue.value());
			metaCompound.setString("valueClass", metaValue.value().getClass().getName());
		} else {
			throw new IllegalArgumentException("Metadata of class " + metaValue.getClass().getName() + " is not supported.");
		}
		
		player.setMetadata(key, metaValue);
	}
	
	/**
	 * Remove a metadata entry for the player
	 * 
	 * @param player the {@link Player}
	 * @param key the meta key
	 * @param plugin the owning {@link Plugin}
	 */
	public static void removeMeta(Player player, String key, Plugin plugin) {
		if (!player.hasMetadata(key)) return;
		
		NBTEntity nbtp = new NBTEntity(player);
		if (!nbtp.hasKey("metadata." + plugin.getName())) return;
		
		NBTCompound compound = nbtp.getCompound("metadata." + plugin.getName());
		compound.removeKey(key);
		
		player.removeMetadata(key, plugin);
	}
	
	public static enum Type implements Serializable {
		BOOLEAN, BYTE, BYTE_ARRAY, DOUBLE, FLOAT, INT_ARRAY, INT, ITEMSTACK, LONG, SHORT, STRING, OBJECT;
	}
}
