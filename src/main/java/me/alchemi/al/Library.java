package me.alchemi.al;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import me.alchemi.al.configurations.Messenger;
import me.alchemi.al.configurations.SexyConfiguration;
import me.alchemi.al.database.mysql.MySQLDatabase;
import me.alchemi.al.objects.ReflectionUtil;
import me.alchemi.al.objects.base.PluginBase;
import me.alchemi.al.objects.commands.PageCommands;
import me.alchemi.al.objects.handling.CarbonDating;
import me.alchemi.al.objects.handling.UpdateChecker;
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
	
	@SuppressWarnings("serial")
	private static final Map<Character, Integer> dictionary = new HashMap<Character, Integer>() {
		{
			put('a', 1);
			put('b', 2);
			put('c', 3);
			put('d', 4);
			put('e', 5);
			put('f', 6);
			put('g', 7);
			put('h', 8);
			put('i', 9);
			put('j', 10);
			put('k', 11);
			put('l', 12);
			put('m', 13);
			put('n', 14);
			put('o', 15);
			put('p', 16);
			put('q', 17);
			put('r', 18);
			put('s', 19);
			put('t', 20);
			put('u', 21);
			put('v', 22);
			put('w', 23);
			put('x', 24);
			put('y', 25);
			put('z', 26);
			put(' ', 27);
			put('-', 28);
			put('_', 29);
			put('?', 30);
			put('*', 31);
		}
	};
	
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
		
		for (String l : Messenger.formatString("&211        00  101001  000110  101101  110011\r\n" + 
				"&20011    0110  11  10    10    01  00  01  01\r\n" + 
				"&201 00  01 00  001010    10    01  11  0010  \r\n" + 
				"&200   01   01  01  00    01    01  01  00  10\r\n" + 
				"&201        00  11  00  100010  111001  11  00\r\n\n" + 
				"&2110000  00    11\r\n" + 
				"&2  00    1011  10\r\n" + 
				"&2  11    10 00 11\r\n" + 
				"&2  00    10  1110\r\n" + 
				"&2  00    01    10\r\n\n" + 
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
	
		getLogger().info(Messenger.formatString("&6Saving player metadata..."));
		for (Player player : Bukkit.getOnlinePlayers()) {
			PersistentMeta.save(player);
		}
		
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
	
	/**
	 * Give an itemstack to a player.
	 * 
	 * @param item		The itemstack to give.
	 * @param player	The player to give it to.
	 * @see {@link ItemStack}, {@link Player}
	 */
	public static void giveItemStack(ItemStack item, Player player) {
		if (item.getAmount() > item.getMaxStackSize()) { 
			ItemStack item2 = item.clone();
			item2.setAmount(item.getAmount() - item.getMaxStackSize());
			giveItemStack(item2, player);
			item.setAmount(item.getMaxStackSize());
		}
		
		if (player.getInventory().firstEmpty() == -1) {
			player.getWorld().dropItem(player.getLocation(), item);
		} else {
			player.getPlayer().getInventory().addItem(item);
		}
	}
	
	/**
	 * Test if a string is a number.
	 * 
	 * @param input the string to test.
	 * @return Wether the string is a number or not.
	 */
	public static boolean testIfNumber(String input) {
		Matcher m = Pattern.compile("\\d+(,|.)*").matcher(input);
		
		return m.matches();
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
	
	public static String toBinary(String from) {
		String output = "";
		from = from.toLowerCase();
		
		for (int ch = 0; ch < from.length(); ch++) {
			output += numToBinary(letterToNumber(from.charAt(ch)));
		}
		return output;
	}
	
	public static int letterToNumber(char input) {
		return dictionary.containsKey(input) ? dictionary.get(input) : -1;
	}
	
	public static String numToBinary(int number) {
		String bin = "";
		
		while (number > 0) {
			for (int entry : Arrays.asList(4, 3, 2, 1, 0)) {
				if (number - Math.pow(2, entry) >= 0) {
					number -= Math.pow(2, entry);
					bin += "1";
					continue;
				}
				bin += "0";
			}
		}
		
		return bin;
	}
	
	public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
	  List<T> list = new ArrayList<T>(c);
	  if (!list.isEmpty()) Collections.sort(list);
	  return list;
	}
	
	public static String itemStackToJson(ItemStack in) {
		// ItemStack methods to get a net.minecraft.server.ItemStack object for serialization
		Class<?> craftItemStackClazz = ReflectionUtil.getOBCClass("inventory.CraftItemStack");
		Method asNMSCopyMethod = ReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);
		Object nmsItemStackObj;
		try {
			nmsItemStackObj = asNMSCopyMethod.invoke(null, in);
			// NMS Method to serialize a net.minecraft.server.ItemStack to a valid Json string
			Class<?> nmsItemStackClazz = ReflectionUtil.getNMSClass("ItemStack");
			Class<?> nbtTagCompoundClazz = ReflectionUtil.getNMSClass("NBTTagCompound");
			Method saveNmsItemStackMethod = ReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);

			Object nmsNbtTagCompoundObj; // This will just be an empty NBTTagCompound instance to invoke the saveNms method
			Object itemAsJsonObject; // This is the net.minecraft.server.ItemStack after being put through saveNmsItem method
			nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance(); // Create the instance
			itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);
			return itemAsJsonObject.toString();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static PersistentMeta getMeta() {
		return meta;
	}
	
	public static Parser getParser() {
		return parser;
	}
}
