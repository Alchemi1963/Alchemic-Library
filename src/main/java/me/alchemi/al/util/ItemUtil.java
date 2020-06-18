package me.alchemi.al.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.tr7zw.changeme.nbtapi.NBTItem;

public class ItemUtil {

	public static String itemStackToJson(ItemStack in) {
		// ItemStack methods to get a net.minecraft.server.ItemStack object for serialization
		Class<?> craftItemStackClazz = ReflectionUtil.getOBCClass("inventory.CraftItemStack");
		Method asNMSCopyMethod = ReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);
		Object nmsItemStackObj;
		try {
			nmsItemStackObj = asNMSCopyMethod.invoke(null, in);
			// NMS Method to serialize a net.minecraft.server.ItemStack to a valid Json string
			Class<?> nmsItemStackClazz = ReflectionUtil.getNMSClass("ItemStack");
			Class<?> nbtTagCompoundClazz = ReflectionUtil.getNMSClass("NBTTagCompound");
			Method saveNmsItemStackMethod = ReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);
	
			Object nmsNbtTagCompoundObj; // This will just be an empty NBTTagCompound instance to invoke the saveNms method
			Object itemAsJsonObject; // This is the net.minecraft.server.ItemStack after being put through saveNmsItem method
			nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance(); // Create the instance
			itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);
			return itemAsJsonObject.toString();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
			return "";
		}
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
	 * Saves custom item identifier to nbt
	 * 
	 * @param item	the input item
	 * @param identifier	the custom item identifier
	 * @return	the new item
	 */
	public static ItemStack customItem(ItemStack item, String identifier) {

		NBTItem nbti = new NBTItem(item);
		nbti.setString("custom_identifier",	identifier);
		return nbti.getItem();
		
	}
	
	/**
	 * Test if the given item is a specific custom item
	 * 
	 * @param item	the input item
	 * @param identifier	the custom item identifier
	 * @return	wether or not the item is the custom item
	 */
	public static boolean isCustomItem(ItemStack item, String identifier) {
		
		NBTItem nbti = new NBTItem(item);
		return nbti.hasNBTData() && nbti.hasKey("custom_identifier") && nbti.getString("custom_identifier").equals(identifier);
		
	}

}
