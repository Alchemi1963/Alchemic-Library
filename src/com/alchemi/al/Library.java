package com.alchemi.al;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Library extends JavaPlugin{
	public static Library instance;
	
	public void onEnable() {
		System.out.println("Hello Stonehenge!");
		instance = this;
		
	}
	
	public void onDisable(){
		System.out.println("I don't wanna go...");
		saveConfig();
	}
	
	public static String fromIndex(String str, int index){
		String newStr = str.substring(index);
		return newStr;
	}
	
	public static boolean containsAny(String string, String regex) {
		for (char c : regex.toCharArray()) {
			if (string.contains(String.valueOf(c))) return true;
		}
		
		return false;
	}
	
	public static boolean checkCmdPermission(Command cmd, CommandSender sender, String permission, String command){
		if (cmd.getName().equalsIgnoreCase(command) && (sender.hasPermission(permission)) || cmd.getName().equalsIgnoreCase(command) && sender.isOp()) {
			return true;
		}
		return false;
	}
}
