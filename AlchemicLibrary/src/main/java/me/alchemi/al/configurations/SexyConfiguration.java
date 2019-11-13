package me.alchemi.al.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;

public class SexyConfiguration extends YamlConfiguration {
	
	protected File file;
	protected String header;
	private int comments;
	
	/**
	 * Create an empty configuration
	 */
	public SexyConfiguration() {}
	
	protected SexyConfiguration(File file) {
		this.setFile(file);
	}
	
	@Override
	public String saveToString() {
		String lastLine = "";
		StringBuilder config;
		
		if (header != null && !header.isEmpty()) config = new StringBuilder(header + "\n");
		else config = new StringBuilder("");
		
		String[] lines = super.saveToString().split("\n");
		
		for (String line : lines) {
			if (line.contains("COMMENT__")) {
				String comment = line.replaceFirst("(COMMENT__\\d+:\\s)", "#").replace("{colon}", ":");
				if (!(config.toString().isEmpty() || lastLine.contains("#"))) config.append("\n" + comment + "\n");
				else config.append(comment + "\n");
				
				lastLine = comment;
			} else {
				config.append(line + "\n");
				lastLine = line;
			}
			
		}
		
		return config.toString().trim();
	}
	
	public static SexyConfiguration loadConfiguration(Reader reader) {
		SexyConfiguration config = new SexyConfiguration();

        try {
            config.load(reader);
        } catch (IOException e) {} 
		catch (InvalidConfigurationException | IllegalArgumentException e) {
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
		try {
			loadFromString(writer.toString());
		} catch (IllegalArgumentException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Invalid configuration file!");
		}
    }
	
	@Override
	public void loadFromString(String contents) throws InvalidConfigurationException {
		
		comments = 0;
		StringBuilder config = new StringBuilder("");
		StringBuilder header = new StringBuilder("");
		for (String line : contents.split("\n")) {
			if (line.contains("#") && !config.toString().isEmpty()) {
				config.append(line.replace(":", "{colon}").replaceFirst("(#)", "COMMENT__" + comments + ": ") + "\n");
				comments ++;
			} else if (line.startsWith("#")) {
				header.append(line + "\n");
			} else {
				config.append(line + "\n");
			}
		}
		this.header = header.toString().trim();
		
		super.loadFromString(config.toString());
	}
	
	/**
	 * Saves the configuration file to the datafolder.
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
	}
	
	/*
	 * Update from the internal yml file
	 */
	public SexyConfiguration update(SexyConfiguration file) throws FileNotFoundException, IOException, InvalidConfigurationException {
		String lastComment = "";
		for (String key : getKeys(true)) if (key.contains("COMMENT__")) lastComment = getString(key);
		
		for (String key : file.getKeys(true)) {
			if (!(this.contains(key) || file.getString(key).equals(lastComment))) {
				this.set(key, file.get(key));
				this.addDefault(key, file.get(key));
			}
		}
		return this;
	}
}
