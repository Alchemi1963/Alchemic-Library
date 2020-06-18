package me.alchemi.al.configurations;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.alchemi.al.Library;
import me.alchemi.al.objects.base.PluginBase;
import me.alchemi.al.objects.meta.ChatPagesMeta;
import me.alchemi.al.objects.placeholder.IStringParser;
import me.alchemi.al.util.NumUtil;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Messenger {

	protected final PluginBase plugin;
	protected @NotNull FileConfiguration messages;
	protected IMessage[] imessages;
	protected String tag;
	protected String header;
	
	/**
	 * Creates a Messenger instance.
	 * 
	 * @param fileManager	The {@link FileManager} to base the Messenger instance from.
	 * @see {@link FileManager}
	 */
	public Messenger(PluginBase plugin) {
		this.plugin = plugin;
		
		if (new File(plugin.getDataFolder(), "messages.yml").exists()) {
			this.messages = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "messages.yml"));
			
			tag = getTag();
			header = getHeader();
			this.messages.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource("messages.yml"))));
			
		} else if (plugin.getResource("messages.yml") != null) {
			
			plugin.saveResource("messages.yml", true);
			this.messages = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "messages.yml"));
			
			tag = getTag();
			header = getHeader();
			this.messages.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource("messages.yml"))));
			
		} else {
			this.messages = null;
			
			tag = generateTag(plugin.getName());
			header = generateHeader(plugin.getName());
		}
		
	}
	
	protected boolean save() {
		try {
			messages.save(new File(plugin.getDataFolder(), "messages.yml"));
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	protected void updateFile(String key) { 
		if (messages.getDefaults().contains(key)) {
			if (!messages.contains(key, true)) {
				messages.set(key, messages.getDefaults().get(key));
				save();
			}
		}
	}
	
	protected static String generateHeader(String pluginName) {
		double l = (52 - pluginName.length())/2;
		if (String.valueOf(l).endsWith(".5")) {
			l = Math.round(l) - 1;
		}
		String header = "";
		for (int i = 0; i < l; i++) {
			header += "=";
		}
		header += pluginName;
		for (int i = 0; i < l; i++) {
			header += "=";
		}
		return header;
	}
	
	private static String generateTag(String pluginName) {
		return "[" + pluginName + "]";
	}
	
	/**
	 * Gets the plugin tag
	 * 
	 * @return The plugin tag, generated one if it doesn't exist
	 */
	public String getTag() {
		tag = messages.getString(plugin.getDescription().getName() + ".Tag");
		
		if (tag == null || tag.isEmpty()) tag = generateTag(plugin.getName());
		
		return tag;
	}
	
	/**
	 * Gets the plugin header
	 * 
	 * @return The plugin header, generated one if it doesn't exist
	 */
	public String getHeader() {
		header = messages.getString(plugin.getDescription().getName() + ".Header");
		if (header == null || header.isEmpty()) {
			
			header = generateHeader(plugin.getName());
			
		}
		return header;
	}
	
	/**
	 * Get a message by key
	 * 
	 * @param key	the key for the message
	 * @return the message
	 */
	public String get(String key) {
		if (!messages.contains(key, true) && messages.getDefaults().contains(key)) {
			updateFile(key);
			return messages.getString(key);
		}		
		return messages.getString(key, "");
	}
	
	/**
	 * Get a message by key
	 * This method asks for multiple keys and concatenates them into a string
	 * 
	 * @param keys	the keys for the message
	 * @return 	the message
	 */
	public String get(String...keys) {
		return get(String.join(".", keys));
	}
	
	/**
	 * Points Messenger to the IMessage implementing enum and loads them in memory.
	 * 
	 * @param imessages
	 */
	public void setMessages(IMessage[] imessages) {
		this.imessages = imessages;
		loadMessages();
	}
	
	protected void loadMessages() {
		for (IMessage imsg : imessages) {
			imsg.setValue(get(imsg.key()));
		}
	}
	
	/**
	 * Reloads the messages file.
	 */
	public void reloadMessages() {
		try {
			messages.load(new File(plugin.getDataFolder(), "messages.yml"));
			tag = getTag();
			header = getHeader();
			loadMessages();
		} catch (IOException | InvalidConfigurationException e) {}
	}
	
	/**
	 * Formats a string for you.
	 * 
	 * @param msg	The string to be coloured
	 * @return		The newly coloured string
	 */
	public static String formatString(String msg){
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	/**
	 * Prints a message to the console, without an instance.
	 * 
	 * @param msg	The message to be displayed
	 */
	public static void printS(Object... msgs) { 
		String msg = "";
		for (Object o : msgs) msg = msg + String.valueOf(o) + "\t";
		Bukkit.getConsoleSender().sendMessage(formatString(msg));
	}
	
	/**
	 * Prints a message to the console, without an instance, but with a tag.
	 * 
	 * @param msg	The message to be displayed
	 * @param tag	The plugin tag to be put in front of the message
	 */
	public static void printS(Object msg, String tag) {
		Bukkit.getConsoleSender().sendMessage(formatString(tag + " " + String.valueOf(msg))); 
	}
	
	/**
	 * Sends a message to the console.
	 * 
	 * @param msg	The message to be sent
	 */
	public void print(Object... msgs) { 
		String msg = "";
		
		for (Object m : msgs) {
			msg += String.valueOf(m) + "\t";
		}
		
		msg = msg.trim();
		
		if (msg.contains("\n")) {
			for (String m : msg.split("\n")) {
				print(m);
			}
			return;
		}
		
		plugin.getLogger().info(formatString(String.valueOf(msg)));
	} 
	
	/**
	 * Broadcasts a message to the whole server.
	 * Assumes using a tag.
	 * 
	 * @param msg The message to be broadcast
	 */
	public void broadcast(IMessage msg) { broadcast(msg.toString()); }
	
	/**
	 * Broadcasts a message to the whole server.
	 * 
	 * @param msg The message to be broadcast
	 * @param useTag Should the tag be displayed
	 */
	public void broadcast(IMessage msg, boolean useTag) {broadcast(msg.toString(), useTag); }
	
	/**
	 * Broadcasts a message to the server.
	 * 
	 * @param msg 		The message to be broadcast
	 * @param useTag 	Should the tag be displayed
	 * @param predicate The statement receivers should test true for.
	 */
	public void broadcast(IMessage msg, boolean useTag, Predicate<Player> predicate) { broadcast(msg.toString(), useTag, predicate); }
	
	/**
	 * Broadcasts a message to the whole server.
	 * Assumes using a tag.
	 * 
	 * @param msg The message to be broadcast
	 */
	public void broadcast(IStringParser msg) { broadcast(msg.create()); }
	
	/**
	 * Broadcasts a message to the whole server.
	 * 
	 * @param msg The message to be broadcast
	 * @param useTag Should the tag be displayed
	 */
	public void broadcast(IStringParser msg, boolean useTag) { broadcast(msg.create(), useTag); }
	
	/**
	 * Broadcasts a message to the server.
	 * 
	 * @param msg 		The message to be broadcast
	 * @param useTag 	Should the tag be displayed
	 * @param predicate The statement receivers should test true for.
	 */
	public void broadcast(IStringParser msg, boolean useTag, Predicate<Player> predicate) { broadcast(msg.create(), useTag, predicate); }
	
	/**
	 * Broadcasts a message to the whole server.
	 * Assumes using a tag.
	 * 
	 * @param msg The message to be broadcast
	 */
	public void broadcast(String msg) {
		broadcast(msg, true, null);
	}
	
	/**
	 * Broadcasts a message to the whole server.
	 * 
	 * @param msg The message to be broadcast
	 * @param useTag Should the tag be displayed
	 */
	public void broadcast(String msg, boolean useTag) {
		broadcast(msg, useTag, null);
	}
	
	/**
	 * Broadcasts a message to the server.
	 * 
	 * @param msg 		The message to be broadcast
	 * @param useTag 	Should the tag be displayed
	 * @param predicate The statement receivers should test true for.
	 */
	public void broadcast(String msg, boolean useTag, Predicate<Player> predicate) {
		
		if (msg.contains("\n")) {
			for (String msg2 : colourMessageInternally(msg).split("\n")) {
				broadcast(msg2, useTag, predicate);
			}
			return;
		}
		
		
		for (Player receiver : Bukkit.getOnlinePlayers()) {
			if (predicate != null && !predicate.test(receiver)) continue;
			 
			String sendingMsg = Library.getParser().parse(receiver, msg);
			if (useTag) {
				if (msg.isEmpty()) return;
				if (tag.endsWith(" ")) receiver.sendMessage(formatString(tag + sendingMsg));
				else receiver.sendMessage(formatString(tag + " " + sendingMsg));
			}
			else receiver.sendMessage(formatString(sendingMsg));
		}
		
	}
	
	private String colourMessageInternally(String msg) {
		Pattern colourCatcher = Pattern.compile("((&[0123456789abcdefklmnor])+)");
		String lastCol = "";
		StringBuilder newMsg = new StringBuilder("");
		for (String subMessage : msg.split("\n")) {
			for (String sub : msg.split(" ")) {
				Matcher subMatcher = colourCatcher.matcher(sub);
				while (subMatcher.find()) {
					lastCol = subMatcher.group();
				}
			}
			newMsg.append(lastCol);
			newMsg.append(subMessage);
		}
		return newMsg.toString();
	}
	
	/**
	 * Sends a string to a {@link CommandSender}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link CommandSender}
	 */
	public void sendMessage(IMessage msg, CommandSender receiver) { sendMessage(msg.toString(), receiver); }
	
	/**
	 * Sends a string to a {@link CommandSender}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link CommandSender}
	 */
	public void sendMessage(IStringParser msg, CommandSender receiver) { sendMessage(msg.create(), receiver); }
	
	/**
	 * Sends a string to a {@link CommandSender}
	 * 
	 * @param msg		The string to be sent
	 * @param reciever	The {@link CommandSender}
	 */
	public void sendMessage(String msg, CommandSender receiver) { 
		
		if (msg.isEmpty()) return;
		
		String sendingMsg = Library.getParser().parse(receiver, msg);
		
		if (tag.endsWith(" ")) receiver.sendMessage(formatString(tag + sendingMsg));
		else receiver.sendMessage(formatString(tag + " " + sendingMsg));
	}
	
	/**
	 * Sends pages to receiver in chat
	 * 
	 * @param receiver
	 * @param msgs
	 */
	public void sendPages(Player receiver, String...msgs) {
		String symbol = header.substring(0, 1);
		Matcher matcher = Pattern.compile("((&[0123456789abcdefklmnor])+.)").matcher(header);
		if (matcher.find()) symbol = matcher.group();
		
		String footer = "==============Page:x/y==============".replace("=", symbol);
		
		String footerEx = new String(new char[4]).replace("\0", symbol);
		
		List<TextComponent> messages = new ArrayList<TextComponent>();
		
		int line = 0;
		int i = 0;
		String msg = "";
		
		while (msgs.length >= i) {
			if (line == 0) {
				msg = formatString(header) + "\n";
				line ++;
			} else if (line == 9 || i == msgs.length) {
				msg += footerEx;
				
				TextComponent main = new TextComponent(formatString(msg));
				
				TextComponent previous = new TextComponent(formatString("&c&oPrev&r"));
				previous.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + NumUtil.toBinary("previous")));
				
				TextComponent between = new TextComponent(formatString(footer
						.replace("x", String.valueOf(messages.size() + 1))
								.replace("y", String.valueOf(Math.round(msgs.length/8.0)))));
				
				TextComponent next = new TextComponent(formatString("&a&oNext&r"));
				next.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + NumUtil.toBinary("next")));
				
				main.addExtra(previous);
				main.addExtra(between);
				main.addExtra(next);
				main.addExtra(formatString(footerEx));
				
				messages.add(main);
				
				msg = "";
				line = 0;
				
				if (i == msgs.length) break;
				
			} else {
				String m = Library.getParser().parse(receiver, msgs[i]);
				if (m != null && !m.isEmpty()) msg += formatString(m) + "\n";
				else msg += "AIR\n";
				line ++;
				i++;
			}
		}
		
		ListIterator<TextComponent> iter = messages.listIterator();
		
		if (iter.hasNext()) receiver.spigot().sendMessage(iter.next());
		
		receiver.setMetadata(ChatPagesMeta.class.getName(), new ChatPagesMeta(iter));
		
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
		for (Player r : Library.getInstance().getServer().getOnlinePlayers()) {
			sendMessageHover(r, tag + " " + mainText, hoverText);
		}
	}
	
	/**
	 * Sends a message with hovering text.
	 * 
	 * @param reciever	The {@link CommandSender} receiver
	 * @param mainText	The main text to be sent
	 * @param hoverText	The text the receiver should see when hovering over the main text
	 */
	public static void sendMessageHover(Player reciever, String mainText, String hoverText) {
		
		if (hoverText.substring(0, 1).equals("\n")) hoverText = hoverText.replaceFirst("\n", "");
		
		mainText = colourMessage(mainText);
		
		TextComponent mainComponent = new TextComponent(formatString(Library.getParser().parse(reciever, mainText)));
		mainComponent.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(formatString(Library.getParser().parse(reciever, hoverText))).create()));
		
		reciever.spigot().sendMessage(mainComponent);
	}
	/**
	 * Colours the rest of a string before it gets chopped.
	 * 
	 * @param message		The string to be coloured
	 * @param defaultCol	The fallback colour if none is found
	 * @return				The newly coloured string
	 */
	public static String colourMessage(String message) {
		
		String newText = "";
		String modifier = "";
		
		Pattern pattern = Pattern.compile("(&[0123456789abcdefklmnor])");
		
		for (String s : message.split("\n")) {
			
			Matcher m = pattern.matcher(s);
			
			while (m.find()) {
				modifier = m.group();
			}
			
			newText += modifier + s + "\n";
			
		}
		
		return newText.substring(0, newText.length() - 1);
		
	}
	
	
}
