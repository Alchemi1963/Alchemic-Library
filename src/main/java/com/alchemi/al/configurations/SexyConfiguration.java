package com.alchemi.al.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;

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
		String[] savedString = super.saveToString().split("\n");
		for (String s : savedString) {
			
			if (s.contains(":") && I < savedString.length - 1 
					&& savedString[I+1].contains(":") ||
					I < savedString.length - 1 && !savedString[I+1].contains("-") &&
					s.contains("-")) output.add(s);
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
				
				if (i != 0) output.add(i, "\n" + comments.get(key));
				
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
			if (!l.isEmpty()) realOutput += l + "\n";
		}
		
		return realOutput;
		
	}
	
	public static SexyConfiguration loadConfiguration(Reader reader) {
		SexyConfiguration config = new SexyConfiguration();

        try {
            config.load(reader);
        } catch (IOException e) {} 
		catch (InvalidConfigurationException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Invalid Configuration file!");
			e.printStackTrace();
		}

        return config;
	}
	
	/**
	 * Does practically the same as the {@link SexyConfiguration} constructor, but this one is preferred.
	 * 
	 * @param file
	 * @return
	 */
	public static SexyConfiguration loadConfiguration(File file) {
		
		SexyConfiguration config = new SexyConfiguration(file);
		try {
			config.load(file);
		} catch (IOException e) {} 
		catch (InvalidConfigurationException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Invalid Configuration file!");
			e.printStackTrace();
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
	
	@Override
	public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
		Validate.notNull(file, "File cannot be null");

        final FileInputStream stream = new FileInputStream(file);

        load(new InputStreamReader(stream, Charsets.UTF_8));
	}
	
	@Override
	public void load(String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
		super.load(file);
	}
	
	@Override
	public void load(Reader reader) throws IOException, InvalidConfigurationException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(reader, writer);
		reader.close();
        loadFromString(writer.toString());
    }
	
	@Override
	public void loadFromString(String contents) throws InvalidConfigurationException {
		
		String[] content = contents.split("\n");
		for (int i = 0; i < content.length; i++) {
			if (Pattern.compile(".+#.+").matcher(content[i]).find()) {
				
				Matcher m = Pattern.compile("(#.+)").matcher(content[i]);
				if (m.find()) {
					setComment(findKey(Arrays.copyOfRange(content, 0, i)), m.group());
				}
				
			} else if (Pattern.compile("#.+").matcher(content[i]).find()) {
				String comment = content[i];
				while (Pattern.compile("#.+").matcher(content[i+1]).find()) {
					i++;
					comment = comment.concat("\n" + content[i]);
				}
				
				setComment(findKey(Arrays.copyOfRange(content, 0, i+2)), comment);
			}
		}
		super.loadFromString(contents);
		if (super.options().header() != null) super.options().header("");
		
	}
	
	private String findKey(String[] contents) {
		
		List<String> content = Lists.reverse(Arrays.asList(contents));

		List<String> reverseKey = new ArrayList<String>();
		Map<String, Integer> keyMap = new HashMap<String, Integer>();
		
		Pattern keyPattern = Pattern.compile("(\\w+:)");
		
		for (int i = 0; i < content.size(); i++) {
			Matcher m = keyPattern.matcher(content.get(i));
			
			if (m.find()) {
				if (reverseKey.isEmpty()) {
					reverseKey.add(m.group().replace(":", ""));
					if (content.get(i).replaceFirst("(\\w.+)", "").length() == 0) return m.group().replace(":", "");
					keyMap.put(reverseKey.get(reverseKey.size() - 1), content.get(i).replaceFirst("(\\w.+)", "").length());
					
				}
				else {
					
					if (content.get(i).replaceFirst("(\\w.+)", "").length() < keyMap.get(reverseKey.get(reverseKey.size() - 1))) {
						reverseKey.add(m.group().replace(";", ""));
						if (content.get(i).replaceFirst("(\\w.+)", "").length() == 0) break;
						keyMap.put(reverseKey.get(reverseKey.size() - 1), content.get(i).replaceFirst("(\\w.+)", "").length());
					}
				}
			}
		}
		
		String key = "";
		for (String subKey : Lists.reverse(reverseKey)) {
			if (key.isEmpty()) key = subKey;
			else key = key.concat("." + subKey);
		}
		
		return key;
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
		} catch (IOException e) {} 
		catch (InvalidConfigurationException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Invalid Configuration file!");
			e.printStackTrace();
		}
	}
	
	public SexyConfiguration update(YamlConfiguration file) throws FileNotFoundException, IOException, InvalidConfigurationException {
		this.load();
		
		for (String key : file.getKeys(true)) {
			if (!this.contains(key)) {
				this.set(key, file.get(key));
				this.addDefault(key, file.get(key));
			}
		}
		
		return this;
	}
}
