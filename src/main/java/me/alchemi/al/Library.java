package me.alchemi.al;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.alchemi.al.configurations.Messenger;
import me.alchemi.al.configurations.SexyConfiguration;
import me.alchemi.al.database.mysql.MySQLDatabase;
import me.alchemi.al.objects.base.PluginBase;
import me.alchemi.al.objects.commands.PageCommands;
import me.alchemi.al.objects.handling.CarbonDating;
import me.alchemi.al.objects.handling.UpdateChecker;
import me.alchemi.al.objects.listeners.ArmourListener;
import me.alchemi.al.objects.meta.PersistentMeta;
import me.alchemi.al.objects.placeholder.PapiParser;
import me.alchemi.al.objects.placeholder.Parser;
import me.alchemi.al.objects.placeholder.RegularParser;

public class Library extends PluginBase implements Listener {
	
	private static Library instance;
	
	private static PersistentMeta meta;
	
	private static Parser parser;
	
	public SexyConfiguration sc;
	
	public UpdateChecker uc;
	
	@Override
	public void onEnable() {
		
		MySQLDatabase.load();
		
		instance = this;
		
		this.SPIGOT_ID = 62777;
		this.setMessenger(new Messenger(this));
		
		uc = new UpdateChecker(this);
		
		meta = new PersistentMeta();
		
		parser = getServer().getPluginManager().getPlugin("PlaceholderAPI") != null ? new PapiParser() : new RegularParser();
		
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new PageCommands(), this);
		Bukkit.getPluginManager().registerEvents(new ArmourListener(), this);
		
		for (String l : Messenger.formatString("&211        00  101001  000110  101101  110011\r\n" + 
				"&20011    0110  11  10    10    01  00  01  01\r\n" + 
				"&201 00  01 00  001010    10    01  11  0010  \r\n" + 
				"&200   01   01  01  00    01    01  01  00  10\r\n" + 
				"&201        00  11  00  100010  111001  11  00\r\n\n" + 
				"&2110000  00    11\r\n" + 
				"&2  00    1011  10\r\n" + 
				"&2  11    10 00 11\r\n" + 
				"&2  00    10  1110\r\n" + 
				"&2100011  01    10\r\n\n" +
				"&201        10  001000  0111    011001  001100\r\n" + 
				"&21011    0101  00      10  11    00    00  01\r\n" + 
				"&200 11  01 01  0000    01  01    00    11  01\r\n" + 
				"&211   01   10  00      10  00    10    11  01\r\n" + 
				"&201        10  010111  1111    001001  111010").split("\n")) {
			Bukkit.getConsoleSender().sendMessage(l);
		}
	}
	
	public static Library getInstance() {
		return instance;
	}
	
	public void onDisable(){
		
		getLogger().info(Messenger.formatString("&7I don't wanna go..."));
		
	}
	
	/**
	 * Gets an OfflinePlayer by name.
	 * isn't deprecated, like {@linkplain Bukkit.getOfflinePlayer(String name)}
	 * 
	 * @param name 	The name of the player
	 * @return		The OfflinePlayer
	 */
	public static OfflinePlayer getOfflinePlayer(String name) {
		for (OfflinePlayer offP : Bukkit.getOfflinePlayers()) {
			if (offP.getName().equals(name)) return offP;
		}
		return null;
	}
	
	
	public static Player getPlayer(String name) {
		name = ChatColor.stripColor(Messenger.formatString(name));
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getName().equals(name) || ChatColor.stripColor(Messenger.formatString(player.getDisplayName())).equals(name)) return player;
		}
		return null;
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("al.forcecheckupdate")) {
			uc.check();
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if ((e.getPlayer().hasPermission("al.beingcool") || e.getPlayer().isOp())
				&& (e.getMessage().equalsIgnoreCase("hello there") || e.getMessage().toLowerCase().contains("hello there"))) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
				
				@Override
				public void run() {
					messenger.broadcast("&7&oGeneral Kenobi...", false);
					CarbonDating date = CarbonDating.getCurrentDateTime();
					if (date.month.equals("11")
							&& (date.day.equals("09") || date.day.equals("9"))) {
						messenger.broadcast("&6&lHappy birthday, &2&l&oA&a&l&ol&2&l&oc&a&l&oh&2&l&oe&a&l&om&2&l&oi&6!", false);
					}
					
				}
			}, 5);
		}
	}
	
	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  if (!list.isEmpty()) Collections.sort(list);
	  return list;
	}
	
	public static PersistentMeta getMeta() {
		return meta;
	}
	
	public static Parser getParser() {
		return parser;
	}
}
