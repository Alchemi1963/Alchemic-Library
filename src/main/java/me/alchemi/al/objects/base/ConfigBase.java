package me.alchemi.al.objects.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.inventory.ItemStack;

import me.alchemi.al.configurations.SexyConfiguration;
import me.alchemi.al.objects.handling.ItemFactory;

/**
 * @author Alchemi
 *
 */
public abstract class ConfigBase {

	protected PluginBase plugin;
	public ConfigBase instance;
	
	public interface IConfig{
		
		Object value();
		
		String key();
		
		SexyConfiguration getConfig();
		
		void get();
		
		default void set(Object value) throws IOException {
			getConfig().set(key(), value);
			getConfig().save();
			get();
		}
		
		default boolean asBoolean() {
			return Boolean.valueOf(asString());
		}
		
		default String asString() {
			return String.valueOf(value());
		}
		
		default Sound asSound() {
			return Sound.valueOf(asString());
		}
		
		@SuppressWarnings("unchecked")
		default List<String> asStringList() {
			if (value() instanceof List) {
				if (((List<?>)value()).get(0) instanceof String
						|| ((List<?>)value()).isEmpty()) {
					return (List<String>)value();
				}
			}
			return new ArrayList<String>();
		}
		
		default int asInt() {
			return Integer.valueOf(asString());
		}
		
		default double asDouble() {
			return Double.valueOf(asString());
		}
		
		@SuppressWarnings("unchecked")
		default List<Float> asFloatList() {
			if (value() instanceof List) {
				if (((List<?>)value()).get(0) instanceof Float
						|| ((List<?>)value()).isEmpty()) {
					return (List<Float>)value();
				}
			}
			return new ArrayList<Float>();
		}
		
		@SuppressWarnings("unchecked")
		default List<Integer> asIntList() {
			if (value() instanceof List) {
				if (((List<?>)value()).get(0) instanceof Integer
						|| ((List<?>)value()).isEmpty()) {
					return (List<Integer>)value();
				}
			}
			return new ArrayList<Integer>();
		}
		
		default ItemStack asItemStack() {
			if (value() instanceof ItemStack) {
				return (ItemStack)value();
			}
			return new ItemFactory(Material.AIR);
		}
		
		default Material asMaterial() {
			return Material.getMaterial(asString().toUpperCase());
		}
	}
	
	public interface IConfigEnum{
		
		SexyConfiguration getConfig();
		
		File getFile();
		
		int getVersion();
		
	}
	
	public ConfigBase(PluginBase plugin) throws FileNotFoundException, IOException, InvalidConfigurationException {
		instance = this;
		this.plugin = plugin;
		
		for (IConfigEnum ce : getConfigs()) {
			
			SexyConfiguration file = ce.getConfig();
			
			int version = ce.getVersion();
			
			if(!file.getFile().exists()) {
				plugin.saveResource(file.getFile().getName(), true);
			}
			
			if(!file.isSet("File-Version-Do-Not-Edit") 
					|| !file.get("File-Version-Do-Not-Edit").equals(version)) {
				plugin.getMessenger().print("Your $file$ is outdated! Updating...".replace("$file$", file.getFile().getName()));
				file.load();
				file.update(SexyConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource(file.getFile().getName()))));
				file.set("File-Version-Do-Not-Edit", version);
				file.save();
				plugin.getMessenger().print("File successfully updated!");
			}
			file.addDefaults(SexyConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource(file.getFile().getName()))));
		}
		
		reload();
	}
	
	public void reload() {
		try {
			if (getConfigs() != null) for (IConfigEnum c : getConfigs()) {
				if (c == null) continue;
				c.getConfig().load();	
			}
			
			if (getEnums() != null) for (IConfig value : getEnums()) {
				if (value == null) continue;
				value.get();
			}
			
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void revertDefault() {
		for (IConfigEnum c : getConfigs()) {
			plugin.getMessenger().print("Reverting $file$ to default.".replace("$file$", c.getFile().getName()));
			plugin.saveResource(c.getFile().getName(), true);
		}
		reload();
	}
	
	public void save() {
		
		if (getEnums() != null) for (IConfig value : getEnums()) {
			if (value == null) continue;
			value.getConfig().set(value.key(), value.value());
		}
		
		try {
			if (getConfigs() != null) for (IConfigEnum c : getConfigs()) {
				if (c == null) continue;
				c.getConfig().save();
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	
	protected abstract IConfigEnum[] getConfigs();
	
	protected abstract Set<IConfig> getEnums();
}
