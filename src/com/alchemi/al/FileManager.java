package com.alchemi.al;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileManager {

    private final JavaPlugin plugin;

    private HashMap<String, FileConfiguration> confs = new HashMap<String, FileConfiguration>();
    private HashMap<String, File> files = new HashMap<String, File>();

    public FileManager(JavaPlugin plugin, String[] names, FileConfiguration...configurations) {
        this.plugin = plugin;
        
        for (int x = 0 ; x < names.length; x++) {
        	
        	this.confs.put(names[x], configurations[x]);
        	this.files.put(names[x], new File(plugin.getDataFolder(), names[x]));
        	
        }
    }
    
    public boolean hasConfig(String file) {
    	return confs.containsKey(file) && files.containsKey(file);
    }
    
    public HashMap<String, FileConfiguration> getFiles() {
		return confs;
	}
    
    public JavaPlugin getPlugin(){ return plugin;}

    public void saveDefaultYML(String yml) {
        if(!files.containsKey(yml) || files.get(yml) == null) {
            this.files.put(yml, new File(plugin.getDataFolder(), yml));
        }
        if(!this.files.get(yml).exists()) {
            plugin.saveResource(yml, false);
        }
    }

    public FileConfiguration getConfig(String yml) {
        if (!confs.containsKey(yml) || confs.get(yml) == null) {
        	
        	reloadConfig(yml);
        	
        }
        return confs.get(yml);
    }

    public void reloadConfig(String yml) {
    	if(!files.containsKey(yml) || files.get(yml) == null) {
            this.files.put(yml, new File(plugin.getDataFolder(), yml));
        }
    	this.confs.put(yml, YamlConfiguration.loadConfiguration(this.files.get(yml)));
        
        // Look for defaults in the jar
        try {
            
        	Reader defConfigStream = new InputStreamReader(plugin.getResource(yml));
            if(defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.confs.get(yml).setDefaults(defConfig);
            }
        } catch(Exception e) {
        	(new Messenger(plugin, this)).print("System could not reload configuration!");
            e.printStackTrace();
        }
    }

    public void saveConfig(String yml) {
        if(!this.files.containsKey(yml) || this.files.get(yml) == null || !this.confs.containsKey(yml) || this.confs.get(yml) == null) {
            return;
        }
        try {
            getConfig(yml).save(this.files.get(yml));
        } catch(IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.files.get(yml), ex);
        }
    }
    
    public void updateConfig(String file) {
        HashMap<String, Object> newConfig = getConfigVals(file);
        FileConfiguration c;
        if(file.equals("config.yml")) {
            c = plugin.getConfig();
        } else {
            c = getConfig(file);
        }
        for(String var : c.getKeys(true)) {
            newConfig.remove(var);
        }
        if(newConfig.size() != 0) {
            for(String key : newConfig.keySet()) {
                c.set(key, newConfig.get(key));
            }
            try {
                c.save(new File(plugin.getDataFolder(), file));
            } catch(IOException ignored) {
            }
        }
    }
    
    public void reloadDefaultConfig() {
    	for (String f : files.keySet()) reloadConfig(f);
    }
    
    public void reloadDefaultConfig(String file) {
    	
    	FileConfiguration c = new YamlConfiguration();
    	try {
			c.loadFromString(stringFromInputStream(plugin.getResource(file)));
		} catch (InvalidConfigurationException ignored) {}
    	
    	try {
			c.save(new File(plugin.getDataFolder(), file));
		} catch (IOException ignored) {}
    	
    }
    
    private HashMap<String, Object> getConfigVals(String file) {
        HashMap<String, Object> var = new HashMap<String, Object>();
        YamlConfiguration c = new YamlConfiguration();
        try {
        	c.loadFromString(stringFromInputStream(plugin.getResource(file)));
        } catch (InvalidConfigurationException ignored) {}
        
        for (String key : c.getKeys(false)) {
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