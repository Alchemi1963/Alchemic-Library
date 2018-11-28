package com.alchemi.al;

import org.bukkit.Bukkit;
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
		
		String msg = this.fm.getConfig("messages.yml").getString(this.plugin.getDescription().getName() + "." + key);
		return cc(msg);
		
	}
	
	public String getTag() {
		String msg = this.fm.getConfig("messages.yml").getString(this.plugin.getDescription().getName() + ".Tag");
		return cc(msg);
	}
	
	public static String cc(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static String parseVars(String msg, String...vals) {
		String[] val = new String[10];
		
		for (int x = 0; x < 10 ; x++) {
			if (vals.length >= x+1 && vals[x] != null) val[x] = vals[x];
			else val[x] = "";
		}
		
		
		while (msg.contains("$player$")) msg = msg.replace("$player$", val[0]);
		while (msg.contains("$sender$")) msg = msg.replace("$sender$", val[1]);
		while (msg.contains("$amount$")) msg = msg.replace("$amount$", val[2]);
		while (msg.contains("$item$")) msg = msg.replace("$item$", val[3]);
		while (msg.contains("$name$")) msg = msg.replace("$name$", val[4]);
		while (msg.contains("$price$")) msg = msg.replace("$price$", val[5]);
		while (msg.contains("$valuta$")) msg = msg.replace("$valuta$", val[6]);
		while (msg.contains("$duration$")) msg = msg.replace("$duration$", val[7]);
		while (msg.contains("$inc$")) msg = msg.replace("$inc$", val[8]);
		while (msg.contains("$reason$")) msg = msg.replace("$reason$", val[9]);
		
		return msg;
	}
	
	public void print(Object msg) { print(msg, true); }
	
	public void print(Object msg, String...vals) {
		print(msg, true, vals);
	}
	
	public void print(Object msg, boolean tag, String...vals) {
		if (tag) Bukkit.getConsoleSender().sendMessage(getTag() + " " + cc(parseVars(msg.toString(), vals)));
		else Bukkit.getConsoleSender().sendMessage(cc(parseVars(msg.toString(), vals)));
	}
		
	public void broadcast(String msg) {
		if (msg.contains("\n")) {
			for (String msg2 : msg.split("\n")) {
				broadcast(msg2);
			}
			return;
		}
		
		for (Player r : Library.instance.getServer().getOnlinePlayers()) {
			sendMsg(getTag() + " " + cc(msg), r);
		}
//		Bukkit.broadcastMessage(getTag() + " " + cc(msg));
		
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
