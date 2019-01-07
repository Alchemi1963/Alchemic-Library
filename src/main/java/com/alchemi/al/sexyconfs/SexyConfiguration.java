package com.alchemi.al.sexyconfs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class SexyConfiguration extends YamlConfiguration {
	
	private HashMap<String, String> comments = new HashMap<String, String>();
	
	private File file;
	
	/**
	 * Create an empty configuration
	 */
	public SexyConfiguration() {}
	
	/**
	 * Create a SexyConfiguration with this file.
	 * However, {@linkplain SexyConfiguration.loadConfiguration} is preferred.
	 * 
	 * @param file File to be used
	 * @see {@link File}
	 */
	public SexyConfiguration(File file) {
		this.setFile(file);
		
		try {
			this.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration!", e);
		}
	}
	
	private SexyConfiguration(File file, boolean loaded) {
		this.setFile(file);
	}
	
	/**
	 * Set a comment on a key.
	 * 
	 * @param key The key to set the comment above
	 * @param comment	The comment
	 */
	public void setComment(String key, String comment) {
		comments.put(key, comment);
	}
	
	@Override
	public String saveToString() {
		
		ArrayList<String> output = new ArrayList<String>();
		int I = 0;
		for (String s : super.saveToString().split("\n")) {
			
			if (s.contains(":") && I < super.saveToString().split("\n").length - 1 
					&& super.saveToString().split("\n")[I+1].contains(":") ||
					I < super.saveToString().split("\n").length - 1 && !super.saveToString().split("\n")[I+1].contains("-") &&
					s.contains("-")) output.add(s + "\n");
			else output.add(s);
			I ++;
		}
		
		ArrayList<String> output2 = new ArrayList<String>();
		
		for (String line : output) {
			
			Matcher m = Pattern.compile("\\b[A-z]*").matcher(line);
			if (line.contains(":") && m.find()) {
				output2.add(m.group());
			} else {
				output2.add(line);
			}
		}
		
		for (String key : this.getKeys(true)) {
			if (comments.containsKey(key)) {
				
				int i = 0;
				for (String subPath : key.split("\\.")) {
					
					for (String testFor : output2.subList(i, output2.size() - 1)) {
						if (subPath.equals(testFor)) {
							i = output2.subList(i, output2.size() - 1).indexOf(testFor) + i;
							break;
						}
					}
				}
				
				if (i != 0) output.add(i, comments.get(key));
				
				output2 = new ArrayList<String>();
				
				for (String line : output) {
					
					Matcher m = Pattern.compile("\\b[A-z]*").matcher(line);
					if (line.contains(":") && m.find()) {
						output2.add(m.group());
					} else {
						output2.add(line);
					}
				}
			}
		}
		
		String realOutput = "";
		for (String l : output) {
			realOutput += l + "\n";
		}
		
		return realOutput;
		
	}
	
	/**
	 * Does practically the same as the {@link SexyConfiguration} constructor, but this one is preferred.
	 * 
	 * @param file
	 * @return
	 */
	public static SexyConfiguration loadConfiguration(File file) {
		
		SexyConfiguration config = new SexyConfiguration(file, true);
		try {
			config.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration!", e);
		}
		
		return config;
		
	}
	
	/**
	 * Loads the file from the internally saved file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	public void load() throws FileNotFoundException, IOException, InvalidConfigurationException {
		this.load(file);
	}
	
	/**
	 * Saves the configuration file to the internally determined file.
	 * 
	 * @throws IOException
	 */
	public void save() throws IOException {
		this.save(file);
	}

	/**
	 * Gets the config file.
	 * 
	 * @return The config file.
	 * @see {@link File}
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets a new config file.
	 * 
	 * @param file New config file to load from.
	 */
	public void setFile(File file) {
		this.file = file;
		try {
			this.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration!", e);
		}
	}
}
