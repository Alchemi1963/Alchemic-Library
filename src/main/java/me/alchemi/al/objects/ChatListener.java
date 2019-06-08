package me.alchemi.al.objects;

import java.util.function.BiFunction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.alchemi.al.configurations.Messenger;

public class ChatListener implements Listener {
	
	private Player holder;
	private String reply = "";
	private BiFunction<Player, String, String> biF;
	
	public ChatListener(JavaPlugin plugin, Player holder, BiFunction<Player, String, String> biF, String request) {
		this.holder = holder;
		this.biF = biF;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		holder.sendMessage(Messenger.formatString(request));
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		this.reply = e.getMessage();
		
		biF.apply(holder, reply);
		
		HandlerList.unregisterAll(this);
	}

}
