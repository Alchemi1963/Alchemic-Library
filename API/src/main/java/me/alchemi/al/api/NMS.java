package me.alchemi.al.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface NMS {
	
	public String itemStackToJSON(ItemStack item);
	
	public void openSign(Sign sign, Player player);
	
	public void removePlayer(Player player);
	
	public void addPlayer(Player player);
	
	public static NMS getNMS() {
		
		String packageName = Bukkit.getServer().getClass().getPackage().getName();
		packageName = packageName.substring(packageName.lastIndexOf('.') + 1);
		
		try {
			Class<?> clazz1 = Class.forName("me.alchemi.al.nms." + packageName + ".MaterialWrapper");
			
			if (clazz1.isEnum() && MaterialWrapper.IMaterialWrapper.class.isAssignableFrom(clazz1)) {
				
				Method setwrapper = clazz1.getMethod("setWrapper");
				setwrapper.invoke(null);
				
			}
			
		} catch (ClassNotFoundException | SecurityException  | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		
		try {
			Class<?> clazz2 = Class.forName("me.alchemi.al.nms." + packageName + ".NMSHandler");
			
			if (NMS.class.isAssignableFrom(clazz2)) {
				return (NMS) clazz2.getConstructor().newInstance();
			}
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
