package com.alchemi.al.configurations;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.core.appender.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.Library;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Messenger{

	protected final JavaPlugin plugin;
	protected SexyConfiguration messages;
	protected String tag;
	
	/**
	 * Creates a Messenger instance.
	 * 
	 * @param fileManager	The {@link FileManager} to base the Messenger instance from.
	 * @see {@link FileManager}
	 */
	
	public Messenger(JavaPlugin plugin) {
		this.plugin = plugin;
		
		if (new File(plugin.getDataFolder(), "messages.yml").exists()) {
			this.messages = SexyConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "messages.yml"));
			tag = getTag();
		} else {
			this.messages = null;
			tag = "[" + plugin.getName() + "]";
		}
		
	}
	
	/**
	 * Creates a Messenger instance, with the option of setting a custom messagesFile.
	 *   
	 * @param fileManager	The {@link FileManager} to base the Messenger instance from.
	 * @param messagesFile	The messages file (should end in .yml) - default is messages.yml
	 * @see {@link FileManager}
	 */
	public Messenger(JavaPlugin plugin, String messagesFile) {
		this.plugin = plugin;
		
		if (new File(plugin.getDataFolder(), messagesFile).exists()) {
			this.messages = SexyConfiguration.loadConfiguration(new File(plugin.getDataFolder(), messagesFile));
			tag = getTag();
		} else {
			this.messages = null;
			tag = "[" + plugin.getName() + "]";
		}
	}
	
	/**
	 * Gets the plugin tag
	 * 
	 * @return The plugin tag, null if it isn't found
	 */
	public String getTag() {
		tag = messages.getString(this.plugin.getDescription().getName() + ".Tag");
		if (tag.isEmpty() || tag.equals("") || tag == null) tag = "[" + plugin.getName() + "]";
		return tag;
	}
	
	/**
	 * Colours a string for you.
	 * 
	 * @param msg	The string to be coloured
	 * @return		The newly coloured string
	 */
	public static String cc(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	/**
	 * Prints a message to the console, without an instance.
	 * 
	 * @param msg	The message to be displayed
	 */
	public static void printStatic(Object msg) { Bukkit.getConsoleSender().sendMessage(cc(String.valueOf(msg))); }
	
	/**
	 * Prints a message to the console, without an instance, but with a tag.
	 * 
	 * @param msg	The message to be displayed
	 * @param tag	The plugin tag to be put in front of the message
	 */
	public static void printStatic(Object msg, String tag) { Bukkit.getConsoleSender().sendMessage(cc(tag + " " + String.valueOf(msg))); }
	
	/**
	 * Sends a message to the console.
	 * 
	 * @param msg	The message to be sent
	 */
	public void print(Object msg) { print(msg, true); }
	
	/**
	 * Sends a message to the console.
	 * 
	 * @param msg	The message to be sent
	 * @param tagged	Whether the plugin tag should be displayed or not
	 */
	public void print(Object msg, boolean tagged) { 
		if (String.valueOf(msg).contains("\n")) {
			for (String m : String.valueOf(msg).split("\n")) {
				print(m, tagged);
			}
			return;
		}
		
		if (tagged) plugin.getLogger().info(cc(String.valueOf(msg)));
		else Bukkit.getConsoleSender().sendMessage(cc(String.valueOf(msg)));
	} 
	
	/**
	 * Broadcasts a message to the whole server.
	 * 
	 * @param msg The message to be broadcast
	 */
	public void broadcast(String msg) {
		broadcast(msg, true);
	}
	
	/**
	 * Broadcasts a message to the whole server.
	 * 
	 * @param msg The message to be broadcast
	 * @param useTag Should the tag be displayed
	 */
	public void broadcast(String msg, boolean useTag) {
		if (msg.contains("\n")) {
			for (String msg2 : msg.split("\n")) {
				broadcast(msg2, useTag);
			}
			return;
		}
		
		
		for (Player reciever : Bukkit.getOnlinePlayers()) {
			if (useTag) {
				if (msg.isEmpty()) return;
				if (tag.endsWith(" ")) reciever.sendMessage(cc(tag + msg));
				else reciever.sendMessage(cc(tag + " " + msg));
			}
			else reciever.sendMessage(cc(msg));
		}
		 
//		Bukkit.getServer().broadcastMessage(Tag + " " + cc(msg));
		
	}
	
	/**
	 * Sends a string to a {@link CommandSender}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link CommandSender}
	 */
	public void sendMessage(String msg, CommandSender reciever){
		if (msg.isEmpty()) return;
		
		if (tag.endsWith(" ")) reciever.sendMessage(cc(tag + msg));
		else reciever.sendMessage(cc(tag + " " + msg));
	}
	
	/**
	 * Broadcasts a message with hovering text.
	 * 
	 * @param mainText	The main text to be broadcast
	 * @param hoverText	The text players should see when hovering over the main text
	 */
	public void broadcastHover(String mainText, String hoverText) {
		
		mainText = colourMessage(mainText);
		
		if (mainText.contains("\n")) {
			for (String msg : mainText.split("\n")) {
				broadcastHover(msg, hoverText);
			}
			return;
		}
		for (Player r : Library.instance.getServer().getOnlinePlayers()) {
			sendHoverMsg(r, tag + " " + mainText, hoverText);
		}
	}
	
	/**
	 * Sends a message with hovering text.
	 * 
	 * @param reciever	The {@link CommandSender} receiver
	 * @param mainText	The main text to be sent
	 * @param hoverText	The text the receiver should see when hovering over the main text
	 */
	public static void sendHoverMsg(Player reciever, String mainText, String hoverText) {
		
		if (hoverText.substring(0, 1).equals("\n")) hoverText = hoverText.replaceFirst("\n", "");
		
		mainText = colourMessage(mainText);
		
		TextComponent mainComponent = new TextComponent(cc(mainText));
		mainComponent.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(cc(hoverText)).create() ));
		
		reciever.spigot().sendMessage(mainComponent);
	}
	
	/**
	 * Colours the rest of a string before it gets chopped.
	 * 
	 * @param message 	The string to be coloured
	 * @return			The newly coloured string
	 */
	public static String colourMessage(String message) { return colourMessage(message, "&9"); }
	
	/**
	 * Colours the rest of a string before it gets chopped.
	 * 
	 * @param message		The string to be coloured
	 * @param defaultCol	The fallback colour if none is found
	 * @return				The newly coloured string
	 */
	public static String colourMessage(String message, String defaultCol) {
		
		String mod = defaultCol;
		String newText = "";
		
		for (String s : message.split(" ")) {
			
			if (s.contains("&")) {
				int i = s.indexOf("&") + 1;
				if (s.charAt(i) != ' ')	mod = "&" + s.charAt(i);
			}
			
			if (message.split(" ")[0].equals(s)) newText = s;
			else {
				
				if (s.contains("&")) newText += " " + s;
				else newText += " " + mod + s;
				
			}
			
			continue;
		}
		
		return newText;
		
	}
	
	/**
	 * Reloads the messages file.
	 */
	public void reloadMessages() {
		try {
			messages.load();
		} catch (IOException | InvalidConfigurationException e) {}
	}
}
