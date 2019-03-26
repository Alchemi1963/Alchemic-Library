package com.alchemi.al.configurations;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.Library;
import com.alchemi.al.deprecated.FileManager;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Messenger{

	private final JavaPlugin plugin;
	private final SexyConfiguration messages;
	
	/**
	 * Creates a Messenger instance.
	 * 
	 * @param fileManager	The {@link FileManager} to base the Messenger instance from.
	 * @see {@link FileManager}
	 */
	public Messenger(JavaPlugin plugin) {
		this.plugin = plugin;
		this.messages = SexyConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "messages.yml"));
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
		this.messages = SexyConfiguration.loadConfiguration(new File(plugin.getDataFolder(), messagesFile));
	}
	
	@Deprecated
	/**
	 * Gets a message from the messages file.
	 * 
	 * @param key 	The key of the message (leave out the plugin name)
	 * @return		The string the key links to
	 */
	public String getMessage(String key) {
		
		String msg = messages.getString(this.plugin.getDescription().getName() + "." + key);
		return msg;
		
	}
	
	/**
	 * Gets the plugin tag
	 * 
	 * @return The plugin tag, null if it isn't found
	 */
	public String getTag() {
		return messages.getString(this.plugin.getDescription().getName() + ".Tag");
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
	
	@Deprecated
	/**
	 * Parse a string with variables.
	 * 
	 * @param msg	The string to be parsed
	 * @param vals	A {@link HashMap} containing the variables and their values
	 * @return		The newly parsed string
	 */
	public static String parseVars(String msg, Map<String, Object> vals) {
		for (Entry<String, Object> ent : vals.entrySet()) {
			while (msg.contains(ent.getKey())) msg = msg.replace(ent.getKey(), String.valueOf(ent.getValue()));	
		}
		
		return msg;
	}
	
	/**
	 * Prints a message to the console, without an instance.
	 * 
	 * @param msg	The message to be displayed
	 */
	public static void printStatic(Object msg) {System.out.println(String.valueOf(msg));}
	
	/**
	 * Prints a message to the console, without an instance, but with a tag.
	 * 
	 * @param msg	The message to be displayed
	 * @param tag	The plugin tag to be put in front of the message
	 */
	public static void printStatic(Object msg, String tag) { System.out.println(tag + " " + String.valueOf(msg)); }
	
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
	 * @param tag	Whether the plugin tag should be displayed or not
	 */
	public void print(Object msg, boolean tag) { 
		if (String.valueOf(msg).contains("\n")) {
			for (String m : String.valueOf(msg).split("\n")) {
				print(m, tag);
			}
			return;
		}
		
		if (tag) Bukkit.getConsoleSender().sendMessage(cc(getTag() + " " + String.valueOf(msg)));
		else Bukkit.getConsoleSender().sendMessage(cc(String.valueOf(msg)));
	} 
	
	@Deprecated
	/**
	 * Sends a message to the console.
	 * 
	 * @param msg	The message to be send
	 * @param vals	A {@link HashMap} containing the variables and their values
	 */
	public void print(Object msg, Map<String, Object> vals) {
		print(msg, true, vals);
	}
	
	@Deprecated
	/**
	 * Sends a message to the console.
	 * 
	 * @param msg	The message to be sent
	 * @param tag	Whether the plugin tag should be displayed or not
	 * @param vals	A {@link HashMap} containing the variables and their values
	 */
	public void print(Object msg, boolean tag, Map<String, Object> vals) {
		if (String.valueOf(msg).contains("\n")) {
			for (String m : String.valueOf(msg).split("\n")) {
				print(m, tag, vals);
			}
			return;
		}
		
		if (tag) Bukkit.getConsoleSender().sendMessage(cc(getTag() + " " + parseVars(String.valueOf(msg), vals)));
		else Bukkit.getConsoleSender().sendMessage(cc(parseVars(String.valueOf(msg), vals)));
	}
	
	@Deprecated
	/**
	 * Broadcasts a message to the whole server.
	 * 
	 * @param msg 	The message to be broadcast
	 * @param vals	A {@link HashMap} containing the variables and their values
	 * @param Tag	Should the tag be displayed.
	 */
	public void broadcast(String msg, Map<String, Object> vals, boolean Tag) {
		broadcast(parseVars(msg, vals), Tag);
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
	 * @param Tag Should the tag be displayed
	 */
	public void broadcast(String msg, boolean Tag) {
		if (msg.contains("\n")) {
			for (String msg2 : msg.split("\n")) {
				broadcast(msg2, Tag);
			}
			return;
		}
		
		
		for (Player r : Bukkit.getOnlinePlayers()) {
			if (Tag) r.sendMessage(cc(getTag() + " " + msg));
			else r.sendMessage(cc(msg));
		}
		 
//		Bukkit.getServer().broadcastMessage(getTag() + " " + cc(msg));
		
	}
	
	/**
	 * Sends a string to a {@link CommandSender}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link CommandSender}
	 */
	public void sendMessage(String msg, CommandSender reciever){
		reciever.sendMessage(cc(getTag() + msg));
	}
	
	@Deprecated
	/**
	 * Broadcasts a message to the whole server.
	 * 
	 * @param msg 	The message to be broadcast
	 * @param vals	A {@link HashMap} containing the variables and their values
	 */
	public void broadcast(String msg, Map<String, Object> vs) {
		broadcast(parseVars(msg, vs), true);
	}
	
	@Deprecated
	/**
	 * Sends a string to a {@link CommandSender}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link CommandSender}
	 */
	public static void sendMsg(String msg, CommandSender reciever){
		reciever.sendMessage(cc(msg));
	}
	
	@Deprecated
	/**
	 * Sends a string to a {@link CommandSender}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link CommandSender}
	 * @param vals		A {@link HashMap} containing the variables and their values
	 */
	public static void sendMsg(String msg, CommandSender reciever, Map<String, Object> vals) {
		reciever.sendMessage(cc(parseVars(msg, vals)));
	}
	
	@Deprecated
	/**
	 * Sends a string to a {@link Player}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link Player}
	 */
	public static void sendMsg(String msg, Player reciever){
		reciever.sendMessage(cc(msg));
	}
	
	@Deprecated
	/**
	 * Sends a string to a {@link Player}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link Player}
	 * @param vals		A {@link HashMap} containing the variables and their values
	 */
	public static void sendMsg(String msg, Player reciever, Map<String, Object> vals) {
		reciever.sendMessage(cc(parseVars(msg, vals)));
	} 
	
	@Deprecated
	/**
	 * Broadcasts a message with hovering text.
	 * 
	 * @param mainText	The main text to be broadcast
	 * @param hoverText	The text players shouls see wehen hovering over the main text
	 * @param vals		A {@link HashMap} containing the variables and their values
	 */
	public void broadcastHover(String mainText, String hoverText, Map<String, Object> vals) {
		
		mainText = colourMessage(mainText);
		
		if (mainText.contains("\n")) {
			for (String msg : mainText.split("\n")) {
				broadcastHover(msg, hoverText, vals);
			}
			return;
		}
		for (Player r : Library.instance.getServer().getOnlinePlayers()) {
			sendHoverMsg(r, getTag() + " " + mainText, hoverText, vals);
		}
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
			sendHoverMsg(r, getTag() + " " + mainText, hoverText);
		}
	}
	
	@Deprecated
	/**
	 * Sends a message with hovering text.
	 * 
	 * @param reciever	The {@link CommandSender} receiver
	 * @param mainText	The main text to be sent
	 * @param hoverText	The text the receiver should see when hovering over the main text 
	 * @param vars		A {@link HashMap} containing the variables and their values
	 */
	public static void sendHoverMsg(Player reciever, String mainText, String hoverText, Map<String, Object> vars) {
		sendHoverMsg(reciever, parseVars(mainText, vars), parseVars(hoverText, vars));
		
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
}
