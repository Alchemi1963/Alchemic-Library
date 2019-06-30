package me.alchemi.al.nms.v1_14_R1;

import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_14_R1.block.CraftSign;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.alchemi.al.api.NMS;
import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.EntityHuman;
import net.minecraft.server.v1_14_R1.NBTTagCompound;
import net.minecraft.server.v1_14_R1.PacketPlayOutOpenSignEditor;
import net.minecraft.server.v1_14_R1.TileEntitySign;

public class NMSHandler implements NMS{

	@Override
	public String itemStackToJSON(ItemStack item) {
		
		net.minecraft.server.v1_14_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
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
