package com.alchemi.al;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Scanner;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FileManager {

	private final JavaPlugin plugin;

	private HashMap<String, FileConfiguration> confs = new HashMap<String, FileConfiguration>();

	public FileManager(JavaPlugin plugin, String[] names, FileConfiguration...configurations) {
		this.plugin = plugin;
		
		for (int x = 0 ; x < names.length; x++) {
			
			this.confs.put(names[x], configurations[x]);
			
		}
	}
	
	public FileManager(JavaPlugin plugin, String name, FileConfiguration fileConfiguration) {

		this.plugin = plugin;
		this.confs.put(name, fileConfiguration);
		
	}

	public boolean hasConfig(String file) {
		return confs.containsKey(file);
	}
	
	public HashMap<String, FileConfiguration> getFiles() {
		return confs;
	}
	
	public JavaPlugin getPlugin(){ return plugin;}

	public void saveDefaultYML(String yml) {
		if (!new File(plugin.getDataFolder(), yml).exists()) plugin.saveResource(yml, false);
	}

	public FileConfiguration getConfig(String yml) {
		if (!confs.containsKey(yml) || confs.get(yml) == null) {
			
			reloadConfig(yml);
			
		}
		return confs.get(yml);
	}

	public void reloadConfig(String yml) {
		
		this.confs.put(yml, YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), yml)));
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
		if(!this.confs.containsKey(yml) || this.confs.get(yml) == null) {
			return;
		}
		
		save(getConfig(yml), yml);
	}
	
	public void updateConfig(String file) {
		HashMap<String, Object> newConfig = getConfigVals(file);
		FileConfiguration c = getConfig(file);
		
		for(String var : c.getKeys(true)) {
			newConfig.remove(var);
		}
		
		if(newConfig.size() != 0) {
			for(String key : newConfig.keySet()) {
				c.set(key, newConfig.get(key));
			}
		}
		
		try {
			c.save(new File(plugin.getDataFolder(), file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateConfig(String file, FileConfiguration conf) {
		
		confs.put(file, conf);
		save(conf, file);
		
	}
	
	public void save(FileConfiguration c, String file) {
		try {
			c.save(new File(plugin.getDataFolder(), file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return;
		
		/*String newConf = c.saveToString();
		
		InputStream vile = plugin.getResource(file);
		Scanner scanner = new Scanner(vile);
		List<String> vile2 = Arrays.asList(scanner.useDelimiter("\\A").next().split("\n"));
		scanner.close();
		
		List<String> nca1 = Arrays.asList(newConf.split("\n"));
		ArrayList<String> nca = new ArrayList<String>();
		nca.addAll(nca1);
		String nc = "";
		for (String s : vile2) {
			
			int index = vile2.indexOf(s);
			
			if (!s.contains("#")) {
				
				if (s.contains(" - ")) continue;
				else if (!s.contains(":")) {
					if (s.endsWith("\n")) nc = nc + s;
					else nc = nc + s + "\n";
					continue;
				}
				int i3 = s.lastIndexOf(":");
				
				for (String s2 : nca) {
					if (s.contains(s2)) {
						s = s2 + "\n";
					}
					if (!s2.contains(":")) continue;
					
					int i = s2.lastIndexOf(":");
					if (s.substring(0, i3).equals(s2.substring(0, i))) {
						
						s = s2 + "\n";
						
						if (vile2.size() > index + 1 && vile2.get(index + 1).contains(" - ")) {
		 				int i2 = nca.indexOf(s2) + 1;
		 				
		 				while (nca.size() > i2 && nca.get(i2).contains(" - ")) {
		 					s = s + nca.get(i2) + "\n";
		 					i2 ++;
		 				}
						}
						nca.remove(s2);
						break;
						
					}
				}
			}
			if (s.endsWith("\n")) nc = nc + s;
			else nc = nc + s + "\n";
			
			
		}	 
		
		if (c.contains("File-Version-Do-Not-Edit")) {
			nc = nc + "File-Version-Do-Not-Edit: " + c.getInt("File-Version-Do-Not-Edit");
		}
		PrintWriter writer;
		try {
			writer = new PrintWriter(new File(plugin.getDataFolder(), file), "UTF-8");
			writer.print(nc);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
	}
	
	public void reloadDefaultConfig() {
		for (String f : confs.keySet()) reloadDefaultConfig(f);
	}
	
	public void reloadDefaultConfig(String file) {
		plugin.saveResource(file, true);
		confs.put(file, YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), file)));
		
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