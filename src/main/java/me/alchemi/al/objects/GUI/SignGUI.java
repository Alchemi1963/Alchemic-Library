package me.alchemi.al.objects.GUI;

import java.util.function.BiFunction;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_13_R2.block.CraftSign;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.alchemi.al.configurations.Messenger;
import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.EntityHuman;
import net.minecraft.server.v1_13_R2.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_13_R2.TileEntitySign;

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
		loc.getBlock().setType(Material.SIGN, false);
		
		sign = (Sign) loc.getBlock().getState();
		sign.setLine(0, Messenger.formatString(request));
		sign.setEditable(true);
		sign.update(true);
		
		holder.sendBlockChange(loc, sign.getBlockData());
		
		CraftSign cSign = (CraftSign) sign;
		
		BlockPosition pos = cSign.getPosition();
		TileEntitySign tileEntity = (TileEntitySign) ((CraftPlayer)holder).getHandle().getWorld().getTileEntity(pos);
		tileEntity.a((EntityHuman)((CraftPlayer)holder).getHandle());
		tileEntity.isEditable = true;
		
		PacketPlayOutOpenSignEditor packet = new PacketPlayOutOpenSignEditor(cSign.getPosition());
		((CraftPlayer)holder).getHandle().playerConnection.sendPacket(packet);
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
		if (e.getBlock().getLocation().distance(loc) <= 2) {
			e.setCancelled(true);
		}
	}
}
