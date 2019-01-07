package com.alchemi.al;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.sexyconfs.SexyConfiguration;

public class FileManager {

	private final JavaPlugin plugin;

	private HashMap<String, SexyConfiguration> confs = new HashMap<String, SexyConfiguration>();

	
	/** 
	 * Create a FileManager instance.
	 * 
	 * @param plugin	The current plugin
	 * @param names		Names of the config files (should end in .yml)
	 * @param configurations	The FileConfigurations
	 * @see	{@link FileConfiguration} & {@link JavaPlugin}
	 */
	public FileManager(JavaPlugin plugin, String[] names, SexyConfiguration...configurations) {
		this.plugin = plugin;
		
		for (int x = 0 ; x < names.length; x++) {
			
			this.confs.put(names[x], configurations[x]);
			
		}
	}
	
	/** 
	 * Create a FileManager instance without existing FileConfigurations
	 * 
	 * @param plugin	The current plugin
	 * @param names		Name(s) of the config files (should end in .yml)
	 * @see	{@link JavaPlugin}
	 */
	public FileManager(JavaPlugin plugin, String...names) {
		this.plugin = plugin;
		
		for (String name : names) {
			this.confs.put(name, new SexyConfiguration(new File(plugin.getDataFolder(), name)));
		}
	}
	
	/** 
	 * Create a FileManager instance with only one existing FileConfiguration.
	 * 
	 * @param plugin 	The current plugin
	 * @param name		Name of the config file (should end in .yml)
	 * @param fileConfiguration	The existing FileConfiguration
	 * @see	{@link FileConfiguration} & {@link JavaPlugin}
	 */
	public FileManager(JavaPlugin plugin, String name, SexyConfiguration fileConfiguration) {
		this.plugin = plugin;
		this.confs.put(name, fileConfiguration);
	}

	/** 
	 * Test if the config file exists
	 * 
	 * @param file Name of the config file (should end in .yml) 
	 * @return	true or false
	 */
	public boolean hasConfig(String file) {
		return confs.containsKey(file);
	}
	
	/** 
	 * Get the files map.
	 * 
	 * @return HashMap of the file names and their respective FileConfigurations
	 * @see {@link FileConfiguration}
	 */
	public HashMap<String, SexyConfiguration> getFiles() {
		return confs;
	}
	
	/** 
	 * Returns the plugin
	 * 
	 * @return The plugin this instance belongs to.
	 * @see {@link JavaPlugin}
	 */
	public JavaPlugin getPlugin(){ return plugin;}

	/** 
	 * Save the default YML of a specific config file
	 * 
	 * @param yml The name of the config file (should end in .yml)
	 */
	public void saveDefaultYML(String yml) {
		if (!new File(plugin.getDataFolder(), yml).exists()) plugin.saveResource(yml, false);
	}
	
	/**
	 * Save all the default YMLs.
	 */
	public void saveDefaultYMLs() {
		for (String ent : confs.keySet()) {
			if (!new File(plugin.getDataFolder(), ent).exists()) plugin.saveResource(ent, false);
			
			confs.put(ent, SexyConfiguration.loadConfiguration(new File(plugin.getDataFolder(), ent)));
		}
	}

	/**
	 * Get a config from name
	 * 
	 * @param yml 	Name of the config file (should end in .yml)
	 * @return		The FileConfiguration
	 */
	public SexyConfiguration getConfig(String yml) {
		if (!confs.containsKey(yml) || confs.get(yml) == null) {
			
			reloadConfig(yml);
			
		}
		return confs.get(yml);
	}

	/**
	 * Reload a config.
	 * 
	 * @param yml The config file (should end in .yml) 
	 */
	public void reloadConfig(String yml) {
		
		this.confs.put(yml, SexyConfiguration.loadConfiguration(new File(plugin.getDataFolder(), yml)));
		// Look for defaults in the jar
		try {
			
			Reader defConfigStream = new InputStreamReader(plugin.getResource(yml));
			if(defConfigStream != null) {
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				this.confs.get(yml).setDefaults(defConfig);
			}
			
		} catch(Exception e) {
			Messenger.printStatic("System could not reload configuration!", "[" + plugin.getName() + "]");
			e.printStackTrace();
		}
	}

	/**
	 * Save a config file.
	 * 
	 * @param yml The config file (should end in .yml)
	 */
	public void saveConfig(String yml) {
		if(!this.confs.containsKey(yml) || this.confs.get(yml) == null) {
			return;
		}
		
		save(getConfig(yml));
	}
	
	/**
	 * Try to update the config file with new values.
	 * 
	 * @param file File to be updated (should end in .yml)
	 */
	public void updateConfig(String file) {
		HashMap<String, Object> newConfig = getConfigVals(file);
		SexyConfiguration c = new SexyConfiguration(new File(plugin.getDataFolder(), file));
		try {
			c.load(new File(plugin.getDataFolder(), file));
		} catch (IOException | InvalidConfigurationException e1) {
			e1.printStackTrace();
			return;
		}
		
		if (c.getValues(false).size() == 0) return;
		
		for(Entry<String, Object> key : newConfig.entrySet()) {
			
			if (!c.contains(key.getKey())) {
				c.set(key.getKey(), key.getValue());
				c.addDefault(key.getKey(), key.getValue());
			}
			
		}
		
		try {
			c.save();
			confs.put(file, c);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Try to save a SexyConfiguration
	 * 
	 * @param config The configuration to be saved.
	 */
	public void save(SexyConfiguration config) {
		try {
			config.save();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return;
	}
	
	/**
	 * Reload all default configs
	 */
	public void reloadDefaultConfig() {
		for (String f : confs.keySet()) reloadDefaultConfig(f);
	}
	
	/**
	 * Reload the default config (from within the plugin)
	 * 
	 * @param file The config to reload (should end in .yml)
	 */
	public void reloadDefaultConfig(String file) {
		plugin.saveResource(file, true);
		confs.put(file, SexyConfiguration.loadConfiguration(new File(plugin.getDataFolder(), file)));
	}
	
	private HashMap<String, Object> getConfigVals(String file) {
		HashMap<String, Object> var = new HashMap<String, Object>();
		SexyConfiguration c = new SexyConfiguration();
		try {
			c.loadFromString(stringFromInputStream(plugin.getResource(file)));
		} catch (InvalidConfigurationException ignored) {}
		
		for (String key : c.getKeys(true)) {
			var.put(key, c.get(key));
		}
		return var;
	}
	
	private String stringFromInputStream(InputStream in) {
		Scanner scanner = new Scanner(in);
		String next = scanner.useDelimiter("\\A").next();
		scanner.close();
		return next;
	}
}