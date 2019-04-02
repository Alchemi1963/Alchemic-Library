package com.alchemi.al.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class SexyLocation {

	private final String world;
	private final double x;
	private final double y;
	private final double z;
	private final float yaw;
	private final float pitch;
	
	public SexyLocation(ConfigurationSection section) {
		
		world = section.getString("world", "world");
		x = section.getDouble("x", 0.0);
		y = section.getDouble("y", 0.0);
		z = section.getDouble("z", 0.0);
		yaw = (float)section.getDouble("yaw", 0.0);
		pitch = (float)section.getDouble("pitch", 0.0);
		
	}
	
	public SexyLocation(Location loc) {
		
		world = loc.getWorld().getName();
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
		yaw = loc.getYaw();
		pitch = loc.getPitch();
		
	}
	
	public static SexyLocation blockSpecific(Location loc) {
		
		loc.setX(loc.getBlockX());
		loc.setY(loc.getBlockY());
		loc.setZ(loc.getBlockZ());
		
		return new SexyLocation(loc);
	}
	
	public ConfigurationSection getSection() {
		
		ConfigurationSection section = new YamlConfiguration();
		
		section.set("world", world);
		section.set("x", x);
		section.set("y", y);
		section.set("z", z);
		section.set("yaw", yaw);
		section.set("pitch", pitch);
		
		return section;
	}
	
	public Location getLocation() {
		World w = Bukkit.getWorld(world);
		if (w == null) return null;
		
		return new Location(w, x, y, z, yaw, pitch);
	}
	
}
