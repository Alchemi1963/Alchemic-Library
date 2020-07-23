package me.alchemi.al.objects.listeners;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.PlayerInventory;

import com.google.common.collect.Sets;

import me.alchemi.al.objects.events.ArmourChangeEvent;
import me.alchemi.al.objects.events.ArmourChangeEvent.ArmourAction;

public class ArmourListener implements Listener {

	private static final Set<InventoryAction> PLACE_SET = Sets.newHashSet(InventoryAction.PLACE_ALL, InventoryAction.PLACE_ONE, InventoryAction.PLACE_SOME);
	private static final Set<InventoryAction> PICKUP_SET = Sets.newHashSet(InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF, InventoryAction.PICKUP_SOME);
	
	@EventHandler
	public void testListen(ArmourChangeEvent e) {
		System.out.println(e.getAction());
		System.out.println(e.getArmour());
		System.out.println(e.getSlot());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getClickedInventory() == null || !(e.getClickedInventory() instanceof PlayerInventory) || e.getWhoClicked().getType() != EntityType.PLAYER) return;
		
		ArmourAction action;
		EquipmentSlot slot;
		
		if (PLACE_SET.contains(e.getAction())
				&& e.getSlotType() == SlotType.ARMOR) {
			action = ArmourAction.EQUIP;
			slot = determineSlot(e.getRawSlot());
		} else if (PICKUP_SET.contains(e.getAction())
				&& e.getSlotType() == SlotType.ARMOR) {
			action = ArmourAction.UNEQUIP;
			slot = determineSlot(e.getRawSlot());
		} else if (e.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
			action = ArmourAction.SWAP;
			slot = determineSlot(e.getRawSlot());
		} else if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
			
			System.out.println(e.getSlotType());
			
			if (e.getSlotType() == SlotType.ARMOR) {
				action = ArmourAction.UNEQUIP;
			} else {
				action = ArmourAction.EQUIP;
			}
			
			if (e.getCurrentItem().getType().toString().toUpperCase().contains("HELMET")) {
				slot = EquipmentSlot.HEAD;
			} else if (e.getCurrentItem().getType().toString().toUpperCase().contains("CHESTPLATE")) {
				slot = EquipmentSlot.CHEST;
			} else if (e.getCurrentItem().getType().toString().toUpperCase().contains("LEGGINGS")) {
				slot = EquipmentSlot.LEGS;
			} else if (e.getCurrentItem().getType().toString().toUpperCase().contains("BOOTS")) {
				slot = EquipmentSlot.FEET;
			} else {
				return;
			}
			
		} else if (e.getAction() == InventoryAction.HOTBAR_SWAP) { 
			
			System.out.println(e.getClickedInventory().getItem(e.getHotbarButton()));
			
			if (e.getSlotType() == SlotType.ARMOR) {
				action = ArmourAction.UNEQUIP;
			} else {
				action = ArmourAction.EQUIP;
			}
			
			if (e.getCurrentItem().getType().toString().toUpperCase().contains("HELMET")) {
				slot = EquipmentSlot.HEAD;
			} else if (e.getCurrentItem().getType().toString().toUpperCase().contains("CHESTPLATE")) {
				slot = EquipmentSlot.CHEST;
			} else if (e.getCurrentItem().getType().toString().toUpperCase().contains("LEGGINGS")) {
				slot = EquipmentSlot.LEGS;
			} else if (e.getCurrentItem().getType().toString().toUpperCase().contains("BOOTS")) {
				slot = EquipmentSlot.FEET;
			} else {
				return;
			}
			
			
		} else {
			return;
		}
		
		if (action == ArmourAction.EQUIP) Bukkit.getPluginManager().callEvent(new ArmourChangeEvent((Player) e.getWhoClicked(), action, slot));
		else Bukkit.getPluginManager().callEvent(new ArmourChangeEvent((Player) e.getWhoClicked(), action, slot, e.getCurrentItem()));
	}
	
	@EventHandler
	public void onItemBreak(PlayerItemBreakEvent e) {
		
	}
	
	private EquipmentSlot determineSlot(int rawSlot) {
		if (rawSlot == 5) return EquipmentSlot.HEAD;
		else if (rawSlot == 6) return EquipmentSlot.CHEST;
		else if (rawSlot == 7) return EquipmentSlot.LEGS;
		else if (rawSlot == 8) return EquipmentSlot.FEET;
		else throw new IllegalArgumentException("Raw slot not in range (5-8).");
	}
	
}
