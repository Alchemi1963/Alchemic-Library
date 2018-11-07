package com.alchemi.al;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileManager {

    private final JavaPlugin plugin;

    private HashMap<String, FileConfiguration> confs = new HashMap<String, FileConfiguration>();
    private HashMap<String, File> files = new HashMap<String, File>();

    public FileManager(JavaPlugin plugin, String[] names, FileConfiguration...configurations) {
        this.plugin = plugin;
        
        for (int x = 0 ; x < names.length ; x++) {
        	
        	this.confs.put(names[x], configurations[x]);
        	this.files.put(names[x], new File(plugin.getDataFolder(), names[x]));
        	
        }
    }

    public FileConfiguration getConfig(){
        if (!confs.containsKey("config.yml")) return null;
    	return plugin.getConfig();
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

    public FileConfiguration getFileConfig(String yml) {
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
            
        	Reader defConfigStream = new InputStreamReader(plugin.getResource("messages.yml"));
            if(defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                this.confs.get(yml).setDefaults(defConfig);
            }
        } catch(Exception e) {
            Messenger.print("System could not reload configuration!", plugin.getDescription().getName());
            e.printStackTrace();
        }
    }

    public void saveConfig(String yml) {
        if(!this.files.containsKey(yml) || this.files.get(yml) == null || !this.confs.containsKey(yml) || this.confs.get(yml) == null) {
            return;
        }
        try {
            getFileConfig(yml).save(this.files.get(yml));
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
            c = getConfig();
        }
        for(String var : c.getKeys(false)) {
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
    
    private HashMap<String, Object> getConfigVals(String file) {
        HashMap<String, Object> var = new HashMap<>();
        /*YamlConfiguration config = new YamlConfiguration();
        try {
            config.loadFromString(stringFromInputStream(AuctionStorm.class.getResourceAsStream("/" + file)));
        } catch(InvalidConfigurationException ignored) {
        }
        for(String key : config.getKeys(false)) {
            var.put(key, config.get(key));
        }*/
        
        if (!this.confs.containsKey(file) || this.confs.get(file) == null) {
        	reloadConfig(file);
        }
        
        for (String key : this.confs.get(file).getKeys(false)) {
        	var.put(key, this.confs.get(file).get(key));
        }
        return var;
    }
    /*
    @SuppressWarnings("resource")
    private String stringFromInputStream(InputStream in) {
        return new Scanner(in).useDelimiter("\\A").next();
    }*/
}