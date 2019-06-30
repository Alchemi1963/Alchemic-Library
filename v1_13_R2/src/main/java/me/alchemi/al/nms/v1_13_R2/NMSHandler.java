package me.alchemi.al.nms.v1_13_R2;

import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_13_R2.block.CraftSign;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.alchemi.al.api.NMS;
import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.EntityHuman;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_13_R2.TileEntitySign;

public class NMSHandler implements NMS{

	@Override
	public String itemStackToJSON(ItemStack item) {
		net.minecraft.server.v1_13_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt = nmsItem.save(nbt);
		
		return nbt.toString();
	}

	@Override
	public void openSign(Sign sign, Player player) {
		
		CraftSign cSign = (CraftSign) sign;
		
		BlockPosition pos = cSign.getPosition();
		TileEntitySign tileEntity = (TileEntitySign) ((CraftPlayer)player).getHandle().getWorld().getTileEntity(pos);
		tileEntity.a((EntityHuman)((CraftPlayer)player).getHandle());
		tileEntity.isEditable = true;
		
		PacketPlayOutOpenSignEditor packet = new PacketPlayOutOpenSignEditor(cSign.getPosition());
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
		
	}
	
}
