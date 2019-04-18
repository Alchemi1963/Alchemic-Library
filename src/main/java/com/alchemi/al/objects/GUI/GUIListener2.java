package com.alchemi.al.objects.GUI;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Charsets;

public class GUIListener2 implements Listener {

	@SuppressWarnings("unused")
	private final JavaPlugin plugin; 
	
	private final GUIBase gui;
	
	public GUIListener2(JavaPlugin plugin, GUIBase gui) {
		this.plugin = plugin;
		this.gui = gui;
		System.out.println("REGISTERED!");
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		
		System.out.println(player);
		System.out.println(gui.getPlayer().getPlayer());
		
		if (!player.equals(gui.getPlayer().getPlayer()) 
				&& !(gui.getSender() instanceof Player && player.equals((Player)gui.getSender()))) return;
		
		if (e.getSlotType() != SlotType.OUTSIDE 
				&& e.getSlotType() != SlotType.QUICKBAR
				&& e.getRawSlot() >= 0 && e.getRawSlot() < gui.getGuiSize()) {
			
			e.setCancelled(true);
			
			try {
				gui.onClicked(e.getSlot(), player, e.getClick());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException e1) {}
			
		}
	}
	
	@EventHandler
	public void onCloseGui(InventoryCloseEvent e) {
		Player player = (Player)e.getPlayer();
		
		if (!player.equals(gui.getPlayer().getPlayer()) 
				&& !(gui.getSender() instanceof Player && player.equals((Player)gui.getSender()))) return;
		
		HandlerList.unregisterAll(this);
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			
			@Override
			public void run() {
				
				gui.onClose();
				
			}
		}, 2);
	}
	
	public static UUID createUUID(String guiTitle, OfflinePlayer player) {
		String pName = player.getName();
		return UUID.nameUUIDFromBytes((guiTitle + "_" + pName).getBytes(Charsets.UTF_8));
	}
	
}
