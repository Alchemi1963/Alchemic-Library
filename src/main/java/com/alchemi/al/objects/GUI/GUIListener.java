package com.alchemi.al.objects.GUI;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIListener implements Listener {

	protected final JavaPlugin plugin; 
	
	protected final GUIBase gui;
	
	public GUIListener(JavaPlugin plugin, GUIBase gui) {
		this.plugin = plugin;
		this.gui = gui;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		
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
}
