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

import com.google.common.base.Charsets;

public class GuiListener implements Listener {

	private final HashMap<UUID, GuiBase> guis = new HashMap<UUID, GuiBase>();
	
	@SuppressWarnings("unused")
	private final JavaPlugin plugin; 
	
	public GuiListener(JavaPlugin plug) {
		plugin = plug;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		Player pl = (Player) e.getWhoClicked();
		
		if (guis.containsKey(createUUID(e.getView().getTitle(), pl)) && e.getSlotType() != SlotType.OUTSIDE) {
			
			e.setCancelled(true);
			
			try {
				guis.get(createUUID(e.getView().getTitle(), pl)).onClicked(e.getSlot(), pl);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| InstantiationException e1) {}
			
		}
	}
	
	@EventHandler
	public void onCloseGui(InventoryCloseEvent e) {
		
		UUID id = createUUID(e.getView().getTitle(), (OfflinePlayer) e.getPlayer());
		if (guis.containsKey(id)) {
			guis.get(id);
		}
	}
	
	public void registerGui(GuiBase gui) {
		guis.put(createUUID(gui.getGuiName(), gui.getPlayer()), gui);
	}
	
	public void unregisterGui(GuiBase gui) {
		UUID id = createUUID(gui.getGuiName(), gui.getPlayer());
		if (guis.containsKey(id)) guis.remove(id);
	}
	
	public GuiBase getGui(String name, OfflinePlayer player) {
		if (guis.containsKey(createUUID(name, player))) return guis.get(createUUID(name, player));
		return null;
	}
	
	protected static UUID createUUID(String guiTitle, OfflinePlayer player) {
		String pName = player.getName();
		return UUID.nameUUIDFromBytes((guiTitle + "_" + pName).getBytes(Charsets.UTF_8));
	}
	
}
