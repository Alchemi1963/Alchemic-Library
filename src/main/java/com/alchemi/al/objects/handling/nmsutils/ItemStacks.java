package com.alchemi.al.objects.handling.nmsutils;

import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_13_R2.NBTTagCompound;

public class ItemStacks {

	public static String itemStackToJSON(ItemStack item) {
		net.minecraft.server.v1_13_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt = nmsItem.save(nbt);
		
		return nbt.toString();
	}
	
}
