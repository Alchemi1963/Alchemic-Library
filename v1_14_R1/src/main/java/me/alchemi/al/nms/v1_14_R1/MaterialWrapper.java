package me.alchemi.al.nms.v1_14_R1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.bukkit.Material;

import me.alchemi.al.api.MaterialWrapper.IMaterialWrapper;

public enum MaterialWrapper implements IMaterialWrapper {

    CACTUS_GEEN(Material.GREEN_DYE),
    DANDELION_YELLOW(Material.YELLOW_DYE),
    SIGN(Material.OAK_SIGN);

    private final Material material;
	
	private MaterialWrapper(Material material) {
		this.material = material;
	}
	
	@Override
	public Material getMaterial() {
		return material;
	}
	
	public static Iterator<Material> getMaterials() {
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
