package com.alchemi.al.objects.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.inventory.ItemStack;

import com.alchemi.al.configurations.SexyConfiguration;

/**
 * @author Alchemi
 *
 */
public abstract class ConfigBase {

	PluginBase plugin;
	ConfigBase instance;
	
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
	
	public ConfigBase(PluginBase plugin) throws FileNotFoundException, IOException, InvalidConfigurationException {
		instance = this;
		this.plugin = plugin;
		
		for (SexyConfiguration file : getConfigs()) {
			
			int version = 0;
			
			if (plugin.getInstance().configs.containsKey(file)) {
				version = plugin.getInstance().configs.get(file).version;
			}
			
			if(!file.getFile().exists()) {
				plugin.getInstance().saveResource(file.getFile().getName(), false);
			}
			
			if(!file.isSet("File-Version-Do-Not-Edit") 
					|| !file.get("File-Version-Do-Not-Edit").equals(version)) {
				plugin.getInstance().getMessenger().print("Your $file$ is outdated! Updating...".replace("$file$", file.getFile().getName()));
				file.load(new InputStreamReader(plugin.getInstance().getResource(file.getFile().getName())));
				file.update(SexyConfiguration.loadConfiguration(new InputStreamReader(plugin.getInstance().getResource(file.getFile().getName()))));
				file.set("File-Version-Do-Not-Edit", version);
				file.save();
				plugin.getInstance().getMessenger().print("File successfully updated!");
			}
		}
		
		reload();
	}
	
	public void reload() {
		try {
			for (SexyConfiguration c : getConfigs()) {
				c.load();	
			}
			
			for (IConfig value : getEnums()) {
				value.get();
			}
			
			for (IMessage value : getMessages()) {
				value.get();
			}
			
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	protected void save() {
		
		for (IConfig value : getEnums()) {
			value.getConfig().set(value.key(), value.value());
		}
		
		for (IMessage value : getMessages()) {
			value.getConfig().set(value.key(), value.value());
		}
		
		try {
			for (SexyConfiguration c : getConfigs()) {
				c.save();
			}
		} catch (IOException e) {e.printStackTrace();}
	}
	
	protected abstract SexyConfiguration[] getConfigs();
	
	protected abstract Set<IConfig> getEnums();
	
	protected abstract Set<IMessage> getMessages();
}
