package me.alchemi.al.objects.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.inventory.ItemStack;

import me.alchemi.al.configurations.SexyConfiguration;

/**
 * @author Alchemi
 *
 */
public abstract class ConfigBase {

	protected PluginBase plugin;
	public ConfigBase instance;
	
	protected interface IConfig{
		
		Object value();
		
		String key();
		
		SexyConfiguration getConfig();
		
		void get();
		
		boolean asBoolean();
		
		String asString();
		
		Sound asSound();
		
		List<String> asStringList();
		
		int asInt();
		
		ItemStack asItemStack();
		
		Material asMaterial();
	}
	
	protected interface IMessage {
		
		public void get();
		
		public String value();
		
		public String key();
		
		SexyConfiguration getConfig();
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
				plugin.saveResource(file.getFile().getName(), false);
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
				
			if (getMessages() != null) for (IMessage value : getMessages()) {
				if (value == null) continue;
				value.get();
			}
			
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	protected void save() {
		
		if (getEnums() != null) for (IConfig value : getEnums()) {
			if (value == null) continue;
			value.getConfig().set(value.key(), value.value());
		}
		
		if (getMessages() != null) for (IMessage value : getMessages()) {
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
	
	protected abstract Set<IMessage> getMessages();
}
