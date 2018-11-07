package com.alchemi.al;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Messenger{

	private final JavaPlugin plugin;
	private final FileManager fm;
	
	public Messenger(JavaPlugin plug, FileManager fileManager) {
		this.plugin = plug;
		this.fm = fileManager;
	}
	
	public String getMessage(String key) {
		
		String msg = this.fm.getFileConfig("messages.yml").getString(this.plugin.getDescription().getName() + "." + key);
		return cc(msg);
		
	}
	
	public String getTag() {
		String msg = this.fm.getFileConfig("messages.yml").getString(this.plugin.getDescription().getName() + ".Tag");
		return cc(msg);
	}
	
	public static String cc(String msg){
		String translated_message = ChatColor.translateAlternateColorCodes('&', msg);
		return translated_message;
	}
	
	public static String parseVars(String msg, String...vals) {
		if (msg.contains("$player$")) msg = msg.replaceAll("$player$", vals[0]);
		if (msg.contains("$sender$")) msg = msg.replaceAll("$sender$", vals[1]);
		if (msg.contains("$amount$")) msg = msg.replaceAll("$amount$", vals[2]);
		if (msg.contains("$item$")) msg = msg.replaceAll("$item$", vals[3]);
		if (msg.contains("$price$")) msg = msg.replaceAll("$price$", vals[4]);
		if (msg.contains("$valuta$")) msg = msg.replaceAll("$valuta$", vals[5]);
		if (msg.contains("$duration$")) msg = msg.replaceAll("$duration$", vals[6]);
		if (msg.contains("$inc$")) msg = msg.replaceAll("$inc$", vals[7]);
		if (msg.contains("$reason$")) msg = msg.replaceAll("$reason$", vals[8]);
		
		return msg;
	}
		
	public static void print(Object msg, String pluginname){
		System.out.println("[" + pluginname + "] " + cc(msg.toString()));
	}
		
	public void broadcast(String msg) {
		if (msg.contains("\n")) {
			for (String msg2 : msg.split("\n")) {
				broadcast(msg2);
			}
			return;
		}
		
		Library.instance.getServer().broadcastMessage(getTag() + cc(msg));
	}
	
	public void broadcast(String msg, String...p) {
		broadcast(parseVars(msg, p));
	}
	
	public static void sendMsg(String msg, Player reciever){
		reciever.sendMessage(cc(msg));
	}
	
	public static void sendMsg(String msg, Player reciever, String...vars) {
		reciever.sendMessage(cc(parseVars(msg, vars)));
	}
	
}
