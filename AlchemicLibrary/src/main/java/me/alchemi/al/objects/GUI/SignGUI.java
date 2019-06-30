package me.alchemi.al.objects.GUI;

import java.util.function.BiFunction;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.alchemi.al.Library;
import me.alchemi.al.api.MaterialWrapper;
import me.alchemi.al.configurations.Messenger;

public class SignGUI implements Listener{

	private Player holder;
	private String reply = "";
	private BiFunction<Player, String, String> biF;
	
	private Sign sign;
	
	private Location loc;
	
	public SignGUI(JavaPlugin plugin, Player holder, BiFunction<Player, String, String> biF, String request) {
		this.holder = holder;
		this.biF = biF;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		loc = holder.getLocation().clone();
		
		while (loc.getBlock().getType() != Material.AIR) {
			loc.add(0, 1, 0);
		}
		
		loc.getBlock().setType(MaterialWrapper.ACACIA_SIGN.getMaterial(), false);
		
		sign = (Sign) loc.getBlock().getState();
		sign.setLine(0, Messenger.formatString(request));
		sign.setEditable(true);
		sign.update(true);
		
		holder.sendBlockChange(loc, sign.getBlockData());
		
		Library.getInstance().NMSHandler.openSign(sign, holder);
	}
	
	@EventHandler
	public void onSignClose(SignChangeEvent e) {
		loc.getBlock().setType(Material.AIR);
		
		for (String s : e.getLines()) {
			reply += s;
		}
		
		biF.apply(holder, reply);
		HandlerList.unregisterAll(this);
	}
	
	@EventHandler
	public void onPhysics(BlockPhysicsEvent e) {
		if (!e.getBlock().getWorld().equals(loc.getWorld())) return;
		
		if (e.getBlock().getLocation().distance(loc) <= 2) {
			e.setCancelled(true);
		}
	}
}
