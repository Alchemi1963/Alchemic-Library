package me.alchemi.al.nms.v1_13_R2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.bukkit.Material;

import me.alchemi.al.api.MaterialWrapper.IMaterialWrapper;

public enum MaterialWrapper implements IMaterialWrapper{
	
	ACACIA_SIGN(Material.SIGN),
	ACACIA_WALL_SIGN(Material.WALL_SIGN),
	ANDESITE_SLAB(Material.STONE_SLAB),
	ANDESITE_STAIRS(Material.STONE_BRICK_STAIRS),
	ANDESITE_WALL(Material.COBBLESTONE_WALL),
	BARREL(Material.SHULKER_BOX),
	BIRCH_SIGN(Material.SIGN),
	BIRCH_WALL_SIGN(Material.WALL_SIGN),
	BLACK_DYE(Material.INK_SAC),
	BLAST_FURNACE(Material.FURNACE),
	BLUE_DYE(Material.LAPIS_LAZULI),
	BRICK_WALL(Material.COBBLESTONE_WALL),
	BROWN_DYE(Material.COCOA_BEANS),
	CAT_SPAWN_EGG(Material.OCELOT_SPAWN_EGG),
	CUT_RED_SANDSTONE_SLAB(Material.RED_SANDSTONE_SLAB),
	CUT_SANDSTONE_SLAB(Material.SANDSTONE_SLAB),
	DARK_OAK_SIGN(Material.SIGN),
	DARK_OAK_WALL_SIGN(Material.WALL_SIGN),
	DIORITE_SLAB(Material.STONE_SLAB),
	DIORITE_STAIRS(Material.STONE_BRICK_STAIRS),
	DIORITE_WALL(Material.COBBLESTONE_WALL),
	END_STONE_BRICK_SLAB(Material.STONE_SLAB),
	END_STONE_BRICK_STAIRS(Material.STONE_BRICK_STAIRS),
	END_STONE_BRICK_WALL(Material.COBBLESTONE_WALL),
	GRANITE_SLAB(Material.STONE_SLAB),
	GRANITE_STAIRS(Material.STONE_BRICK_STAIRS),
	GRANITE_WALL(Material.COBBLESTONE_WALL),
	GREEN_DYE(Material.CACTUS_GREEN),
	JUNGLE_SIGN(Material.SIGN),
	JUNGLE_WALL_SIGN(Material.WALL_SIGN),
	LANTERN(Material.TORCH),
	MOSSY_COBBLESTONE_SLAB(Material.COBBLESTONE_SLAB),
	MOSSY_COBBLESTONE_STAIRS(Material.COBBLESTONE_SLAB),
	MOSSY_STONE_BRICK_SLAB(Material.STONE_BRICK_SLAB),
	MOSSY_STONE_BRICK_STAIRS(Material.STONE_BRICK_STAIRS),
	MOSSY_STONE_BRICK_WALL(Material.COBBLESTONE_WALL),
	NETHER_BRICK_WALL(Material.COBBLESTONE_WALL),
	OAK_SIGN(Material.SIGN),
	OAK_WALL_SIGN(Material.WALL_SIGN),
	POLISHED_ANDESITE_SLAB(Material.STONE_SLAB),
	POLISHED_ANDESITE_STAIRS(Material.STONE_BRICK_STAIRS),
	POLISHED_DIORITE_SLAB(Material.STONE_SLAB),
	POLISHED_DIORITE_STAIRS(Material.STONE_BRICK_STAIRS),
	POLISHED_GRANITE_SLAB(Material.STONE_SLAB),
	POLISHED_GRANITE_STAIRS(Material.STONE_BRICK_STAIRS),
	PRISMARINE_WALL(Material.COBBLESTONE_WALL),
	RED_DYE(Material.ROSE_RED),
	RED_NETHER_BRICK_SLAB(Material.STONE_SLAB),
	RED_NETHER_BRICK_STAIRS(Material.STONE_BRICK_STAIRS),
	RED_NETHER_BRICK_WALL(Material.COBBLESTONE_WALL),
	RED_SANDSTONE_WALL(Material.COBBLESTONE_WALL),
	SANDSTONE_WALL(Material.COBBLESTONE_WALL),
	SCAFFOLDING(Material.LADDER),
	SMOOTH_QUARTZ_SLAB(Material.QUARTZ_SLAB),
	SMOOTH_QUARTZ_STAIRS(Material.QUARTZ_STAIRS),
	SMOOTH_RED_SANDSTONE_SLAB(Material.RED_SANDSTONE_SLAB),
	SMOOTH_RED_SANDSTONE_STAIRS(Material.SANDSTONE_STAIRS),
	SMOOTH_SANDSTONE_SLAB(Material.SANDSTONE_SLAB),
	SMOOTH_SANDSTONE_STAIRS(Material.SANDSTONE_STAIRS),
	SMOOTH_STONE_SLAB(Material.STONE_SLAB),
	SPRUCE_SIGN(Material.SIGN),
	SPRUCE_WALL_SIGN(Material.WALL_SIGN),
	STONE_BRICK_WALL(Material.COBBLESTONE_WALL),
	STONE_STAIRS(Material.STONE_BRICK_STAIRS),
	WHITE_DYE(Material.BONE_MEAL),
	YELLOW_DYE(Material.DANDELION_YELLOW);

	private final Material material;
	
	private MaterialWrapper(Material material) {
		this.material = material;
		
	}
	
	@Override
	public Material getMaterial() {
		return material;
	}
	
	static Iterator<Material> getMaterials() {
		return Arrays.asList(values()).stream()
				.map(MaterialWrapper -> MaterialWrapper.getMaterial())
				.collect(Collectors.toList()).iterator();
	}
	
	public static void setWrapper() {
		for (me.alchemi.al.api.MaterialWrapper wrapper : me.alchemi.al.api.MaterialWrapper.values()) {
			Material material = Material.getMaterial(wrapper.toString());
			if (material == null && valueOf(wrapper.toString()) == null) {
				wrapper.setMaterial(Material.BARRIER);
				continue;
			} else if (material == null) {
				material = valueOf(wrapper.toString()).getMaterial();
			}
			wrapper.setMaterial(material);
		}
	}

}
