package com.alchemi.al;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.alchemi.al.configurations.Messenger;
import com.alchemi.al.configurations.SexyConfiguration;
import com.alchemi.al.objects.base.PluginBase;
import com.alchemi.al.objects.handling.UpdateChecker;
import com.alchemi.al.objects.meta.PersistentMeta;

public class Library extends PluginBase implements Listener {
	
	public static Library instance;
	
	public SexyConfiguration sc;
	
	public UpdateChecker uc;
	
	@Override
	public void onEnable() {
		instance = this;
		
		this.SPIGOT_ID = 62777;
		this.setMessenger(new Messenger(this));
		
		uc = new UpdateChecker(this);
		
		PersistentMeta.enable();
		
		Bukkit.getPluginManager().registerEvents(this, this);
		 
		for (String l : Messenger.cc("&2 __       __   ______   ______   ______   _______         ______  __    __        __       __  ________  _______   ______   ______  \r\n" + 
				"&2/  \\     /  | /      \\ /      | /      \\ /       \\       /      |/  \\  /  |      /  \\     /  |/        |/       \\ /      | /      \\ \r\n" + 
				"&200  \\   /10 |/111011  |111010/ /100111  |1010111  |      100110/ 10  \\ 10 |      01  \\   /10 |01101011/ 0110100  |001110/ /011101  |\r\n" + 
				"&2101  \\ /000 |11 |__11 |  11 |  11 |  11 |10 |__00 |        01 |  011  \\10 |      010  \\ /000 |01 |__    01 |  11 |  00 |  10 |  01 |\r\n" + 
				"&20100  /0101 |01    11 |  01 |  10 |  10 |01    11<         01 |  0011  11 |      0001  /1011 |01    |   10 |  11 |  11 |  01 |  00 |\r\n" + 
				"&201 10 00/01 |00000100 |  01 |  11 |  00 |0111011  |        01 |  10 01 11 |      00 10 10/00 |00110/    10 |  10 |  01 |  00 |  10 |\r\n" + 
				"&201 |000/ 00 |10 |  01 | _00 |_ 01 \\__11 |01 |  11 |       _00 |_ 10 |0011 |      11 |100/ 00 |00 |_____ 11 |__11 | _10 |_ 00 \\__11 |\r\n" + 
				"&201 | 0/  11 |11 |  01 |/ 11   |00    01/ 00 |  01 |      / 11   |00 | 011 |      10 | 1/  11 |10       |01    01/ / 10   |11    01/ \r\n" + 
				"&211/      00/ 10/   11/ 000010/  101111/  10/   01/       101000/ 01/   00/       00/      10/ 11011011/ 1111011/  001110/  011101/").split("\n")) {
			Bukkit.getConsoleSender().sendMessage(l);
		}
		
	}
	
	public void onDisable(){
	
		getLogger().info(Messenger.cc("&6Saving player metadata..."));
		for (Player player : Bukkit.getOnlinePlayers()) {
			PersistentMeta.save(player);
		}
		
		getLogger().info(Messenger.cc("&7I don't wanna go..."));
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
	 * Test if a string is an integer.
	 * 
	 * @param input the integer to test
	 * @return Wether the string is an integer or not.
	 */
	public static boolean testIfInt(String input) {
		try {
			Integer.valueOf(input);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Test if a string is a number.
	 * 
	 * @param input the string to test.
	 * @return Wether the string is a number or not.
	 */
	public static boolean testIfNumber(String input) {
		try {
			Float.valueOf(input);
			return true;
		} catch(NumberFormatException e) {
			try {
				Double.valueOf(input);
				return true;
			} catch (NumberFormatException ex) {
				try {
					Integer.valueOf(input);
					return true;
				} catch (NumberFormatException exc) {
					try {
						Long.valueOf(input);
						return true;
					} catch (NumberFormatException exce) {
						return false;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("al.forcecheckupdate")) {
			uc.check();
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		if (e.getPlayer().hasPermission("al.beingcool") 
				&& e.getMessage().equalsIgnoreCase("hello there")) {
			messenger.broadcast("&8&oGeneral Kenobi...", false);
		}
	}
}
