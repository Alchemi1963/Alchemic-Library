package me.alchemi.al.objects.GUI;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.alchemi.al.objects.handling.ItemFactory;
import me.alchemi.al.objects.handling.SexyRunnable;
import me.alchemi.al.objects.meta.GUIPageMeta;
import me.alchemi.al.objects.meta.PersistentMeta;

public abstract class GUIBase {
	
	protected String guiName = "";
	protected int guiSize = 0;
	protected static JavaPlugin plugin;
	protected final Player owningPlayer;
	protected final Player sender;
	protected Inventory gui;
	
	protected HashMap<Integer, ItemStack> contents = new HashMap<Integer, ItemStack>();
	protected HashMap<Integer, SexyRunnable> commands = new HashMap<Integer, SexyRunnable>();
	protected HashMap<Integer, Object[]> arguments = new HashMap<Integer, Object[]>();
	
	protected ItemStack nextPage = new ItemFactory(Material.REDSTONE_TORCH).setName("Next Page");
	protected ItemStack prevPage = new ItemFactory(Material.LEVER).setName("Previous Page");
	
	public GUIBase(JavaPlugin plug, String name, int size, Player player, Player sender) {
		GUIBase.plugin = plug;
		guiName = name;
		guiSize = size;
		gui = Bukkit.createInventory(null, guiSize, guiName);
		this.sender = sender;
		this.owningPlayer = player;
	}
	
	public void openGUI() {
		generateGUI();
		sender.openInventory(gui);
	}
	
	public void generateGUI() {
		int page = 0;
		
		if (!PersistentMeta.hasMeta(sender, GUIPageMeta.class)) sender.setMetadata(GUIPageMeta.class.getName(), new GUIPageMeta(GUIBase.plugin, page));
		else page = PersistentMeta.getMeta(sender, GUIPageMeta.class).asInt();
		
		TreeMap<Integer, ItemStack> mapped = new TreeMap<>(contents);
		
		for (Entry<Integer, ItemStack> ent : mapped.entrySet()) {
			if (mapped.lastKey() > guiSize - 1) {
				int pageMax = mapped.lastKey()/(guiSize-9) > Integer.valueOf(Float.valueOf(mapped.lastKey()/(guiSize-9)).toString().replaceAll("\\..*", "")) ? Integer.valueOf(Float.valueOf(mapped.lastKey()/(guiSize-9)).toString().replaceAll("\\..*", "")) + 1 : Integer.valueOf(Float.valueOf(mapped.lastKey()/(guiSize-9)).toString().replaceAll("\\..*", "")); 
				
				if (ent.getKey() <= guiSize - 9 && page == 0) gui.setItem(ent.getKey(), ent.getValue());
				else if (page >= 1) {
					
					int newPlace = ent.getKey() - page * (guiSize - 9);
					if (newPlace >= 0 && newPlace < guiSize - 9) {
						gui.setItem(newPlace, ent.getValue());
					}
				}
				
				if (page != 0) gui.setItem(guiSize - 9, prevPage);
				if (page != pageMax) gui.setItem(guiSize - 1, nextPage);
				
			} else {
				gui.setItem(ent.getKey(), ent.getValue());
			}
		}
	}
	
	public void updateGUI() {
		gui.clear();
		generateGUI();
		sender.updateInventory();
	}
	
	public void onClicked(int slot, ClickType click) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		if (nextPage.isSimilar(gui.getItem(slot))) {
			pageNext.run(sender);
			return;
		} else if (prevPage.isSimilar(gui.getItem(slot))) {
			pagePrev.run(sender);
			return;
		}
		
		if (!contents.containsKey(slot) || !commands.containsKey(slot)) sender.playSound(sender.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
		
		else {
			
			SexyRunnable m = commands.get(slot);
			if (arguments.containsKey(slot)) {
				Object[] args = arguments.get(slot);
				
				int i = 0;
				for (Object arg : arguments.get(slot)) {
					if (arg.equals("<player>")) {
						args[i] = sender;
					}
					i++;
				}
				
				m.run(args);
			}
			else m.run();
			
		}
	}
	
	public void onOutsideClick(int slot, Inventory inventory) {}
	public abstract void setContents();
	public abstract void setCommands();
	public abstract void onClose();
	
	public String getGuiName() {
		return guiName;
	}
	
	public int getGuiSize() {
		return guiSize;
	}
	
	public Inventory getGui() {
		return gui;
	}
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return owningPlayer;
	}

	/**
	 * @return the sender
	 */
	public Player getSender() {
		return sender;
	}

	protected void putArgument(Integer key, Object...args) {
		arguments.put(key, args);
	}

	SexyRunnable pageNext = new SexyRunnable() { 
		
		@Override
		public void run(Object... args) {
			// player
			gui.clear();
			int page = PersistentMeta.getMeta((Player) args[0], GUIPageMeta.class).asInt();
			page ++;
			((Player) args[0]).setMetadata(GUIPageMeta.class.getName(), new GUIPageMeta(GUIBase.plugin, page));
			updateGUI();
		}
	};
	 
	SexyRunnable pagePrev = new SexyRunnable() {
		
		@Override
		public void run(Object... args) {
			// player
			gui.clear();
			int page = PersistentMeta.getMeta((Player) args[0], GUIPageMeta.class).asInt();
			page --;
			((Player) args[0]).setMetadata(GUIPageMeta.class.getName(), new GUIPageMeta(GUIBase.plugin, page));
			updateGUI();
		}
	};
}
