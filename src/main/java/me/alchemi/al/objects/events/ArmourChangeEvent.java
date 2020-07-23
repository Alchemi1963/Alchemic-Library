package me.alchemi.al.objects.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ArmourChangeEvent extends Event {

	public static final HandlerList handlers = new HandlerList();
	
	private final Player player;
	private final ArmourAction action;
	private final EquipmentSlot slot;
	private final ItemStack armour;
	
	public ArmourChangeEvent(Player player, ArmourAction action, EquipmentSlot slot) {
		this(player, action, slot, null);
	}
	
	public ArmourChangeEvent(Player player, ArmourAction action, EquipmentSlot slot, ItemStack armour) {
		this.player = player;
		this.action = action;
		this.slot = slot;
		this.armour = armour;
	}
	
	@Override
	public @NotNull String getEventName() {
		return "ArmourChangeEvent";
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ArmourAction getAction() {
		return action;
	}
	
	public EquipmentSlot getSlot() {
		return slot;
	}
	
	public ItemStack getArmour() {
		if (action == ArmourAction.EQUIP) {
			return player.getInventory().getItem(slot);
		}
		return armour;
	}
	
	public ItemStack getHelmet() {
		return player.getInventory().getHelmet();
	}
	
	public ItemStack getChestplate() {
		return player.getInventory().getChestplate();
	}
	
	public ItemStack getLeggings() {
		return player.getInventory().getLeggings();
	}
	
	public ItemStack getBoots() {
		return player.getInventory().getBoots();
	}
	
	@Override
	public @NotNull HandlerList getHandlers() {
		return handlers;
	}
	
	@NotNull
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public static enum ArmourAction {
		EQUIP, UNEQUIP, BREAK, SWAP;
	}

}
