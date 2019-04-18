package com.alchemi.al.objects.GUI;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.configurations.Messenger;
import com.google.common.base.Charsets;

public class GUIListener implements Listener {

	private final HashMap<UUID, GUIBase> guis = new HashMap<UUID, GUIBase>();
	
	@SuppressWarnings("unused")
	private final JavaPlugin plugin; 
	
	public GUIListener(JavaPlugin plug) {
		plugin = plug;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		Player pl = (Player) e.getWhoClicked();
		
		if (guis.containsKey(createUUID(e.getView().getTitle(), pl)) 
				&& e.getSlotType() != SlotType.OUTSIDE 
				&& e.getSlotType() != SlotType.QUICKBAR
				&& e.getRawSlot() >= 0 && e.getRawSlot() < e.getView().getTopInventory().getSize()) {
			
			e.setCancelled(true);
			
			try {
				guis.get(createUUID(e.getView().getTitle(), pl)).onClicked(e.getSlot(), pl, e.getClick());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException e1) {}
			
		}
	}
	
	@EventHandler
	public void onCloseGui(InventoryCloseEvent e) {
		
		UUID id = createUUID(e.getView().getTitle(), (OfflinePlayer) e.getPlayer());
		if (guis.containsKey(id)) {
			guis.get(id).onClose();
		}
	}
	
	public void registerGui(GUIBase gui) {
		guis.put(createUUID(Messenger.cc(gui.getGuiName()), gui.getPlayer()), gui);
	}
	
	public void unregisterGui(GUIBase gui) {
		UUID id = createUUID(Messenger.cc(gui.getGuiName()), gui.getPlayer());
		if (guis.containsKey(id)) guis.remove(id);
	}
	
	public GUIBase getGui(String name, OfflinePlayer player) {
		if (guis.containsKey(createUUID(name, player))) return guis.get(createUUID(name, player));
		return null;
	}
	
	public static UUID createUUID(String guiTitle, OfflinePlayer player) {
		String pName = player.getName();
		return UUID.nameUUIDFromBytes((guiTitle + "_" + pName).getBytes(Charsets.UTF_8));
	}
	
}
