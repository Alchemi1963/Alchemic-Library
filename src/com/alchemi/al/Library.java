package com.alchemi.al;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.sexyconfs.SexyConfiguration;

public class Library extends JavaPlugin{
	public static Library instance;
	
	public SexyConfiguration sc;
	
	public void onEnable() {
		System.out.println("Hello Stonehenge!");
		instance = this;
		
		saveResource("sc.yml", true);
		sc = SexyConfiguration.loadConfiguration(new File(getDataFolder(), "sc.yml"));
		sc.setSource(getResource("sc.yml"));
		try {
			sc.save(new File(getDataFolder(), "sc.yml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
}
