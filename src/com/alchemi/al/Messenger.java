package com.alchemi.al;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
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
	
	public static String parseVars(String msg, Map<String, String> vals) {
		for (Entry<String, String> ent : vals.entrySet()) {
			
			while (msg.contains(ent.getKey())) msg = msg.replace(ent.getKey(), ent.getValue());	
		}
		
		return msg;
	}
	
	public void print(Object msg) { print(msg, true, new HashMap<String, String>()); }
	
	public void print(Object msg, boolean tag) { print(msg, tag, new HashMap<String, String>()); } 
	
	public void print(Object msg, Map<String, String> vals) {
		print(msg, true, vals);
	}
	
	public void print(Object msg, boolean tag, Map<String, String> vals) {
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
	
	public void broadcast(String msg, Map<String, String> vs) {
		broadcast(parseVars(msg, vs));
	}
	
	public static void sendMsg(String msg, CommandSender reciever){
		reciever.sendMessage(cc(msg));
	}
	
	public static void sendMsg(String msg, CommandSender reciever, Map<String, String> vars) {
		reciever.sendMessage(cc(parseVars(msg, vars)));
	}
	
}
