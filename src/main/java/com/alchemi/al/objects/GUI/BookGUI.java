package com.alchemi.al.objects.GUI;

import java.util.function.BiFunction;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.configurations.Messenger;

public class BookGUI implements Listener{

	private final BiFunction<Player, String, String> function;
	
	private BookMeta meta;
	private Player holder;
	private ItemStack old;
	private String reply = "";
	
	public BookGUI(JavaPlugin plugin, Player holder, BiFunction<Player, String, String> biF, String request) {
		function = biF;
		
		ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
		this.meta = (BookMeta) book.getItemMeta();
		book.setItemMeta(meta);
		
		this.holder = holder;
		
		this.old = holder.getInventory().getItemInMainHand().clone();
		holder.getInventory().setItemInMainHand(book);
		
		holder.sendMessage(Messenger.cc(request));
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void onBookClose(PlayerEditBookEvent e) {
		holder.getInventory().setItemInMainHand(old);
		
		for (String s : e.getNewBookMeta().getPages()) {
			reply += Messenger.cc(s);
		}
		
		function.apply(holder, reply);
		HandlerList.unregisterAll(this);
	}
	
	
	
}
