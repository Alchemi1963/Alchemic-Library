package me.alchemi.al.configurations;

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
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class SexyConfiguration extends YamlConfiguration {
	
	protected HashMap<String, String> comments = new HashMap<String, String>();
	
	protected File file;
	
	/**
	 * Create an empty configuration
	 */
	public SexyConfiguration() {}
	
	protected SexyConfiguration(File file) {
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
		Pattern keyPattern = Pattern.compile("\\b.*(?=:)");
		ArrayList<String> output = new ArrayList<String>(Arrays.asList(super.saveToString().split("\n")));
		
		ArrayList<String> fakeOutput = new ArrayList<String>();
		
		for (String line : output) {
			
			Matcher m = keyPattern.matcher(line);
			
			if (m.find()) {
				fakeOutput.add(m.group());
			} else {
				fakeOutput.add(line);
			}
			
		}
		
		for (String key : this.getKeys(true)) {
			
			if (comments.containsKey(key)) {
				int i = 0;
				String comment = comments.get(key);
				
				for (String subPath : key.split("\\.")) {
					
					for (String testFor : fakeOutput.subList(i, fakeOutput.size())) {
						
						if (subPath.equals(testFor)) {
							i = fakeOutput.subList(i, fakeOutput.size()).indexOf(testFor) + i;
							break;
						}
					}
				}

				if (calculateTabsOrSpaces(output.get(i)) > 0) {
					String tabs;
					if (areTabsUsed(output.get(i))) {
						tabs = new String(new char[calculateTabsOrSpaces(output.get(i))]).replace("\0", "\t");
					} else {
						tabs = new String(new char[calculateTabsOrSpaces(output.get(i))]).replace("\0", " ");
					}
					
					comment = "";
					for (String c : comments.get(key).split("\n")) {
						comment += tabs + c + "\n";
					}
					comment = comment.substring(0, comment.length() - 1);
					
				}
				
				output.set(i, comment + "\n" + output.get(i));
			}
		}
		return listToString(output);
	}
	

	protected static final String listToString(List<String> input) {
		String outputString = "";
		String previous = "";
		for (String l : input) {
			
			if (( (l.isEmpty() || l.replaceAll("\\s", "").isEmpty())
					& !(previous.endsWith("|1-")))) continue;
			
			if ((calculateTabsOrSpaces(previous) != calculateTabsOrSpaces(l))
					|| (!(previous.isEmpty() || previous.replaceAll("\\s", "").isEmpty())
							&& l.contains("#"))) outputString += "\n";
			
			outputString += l + "\n";
			
			previous = l;
		}
		return outputString;
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
		
		List<String> contentList = Arrays.asList(contents.split("\n"));
		ListIterator<String> content = contentList.listIterator();
		
		while (content.hasNext()) {
			
			String current = content.next();
			
			if (Pattern.compile(".+#.+").matcher(current).find()) {
				
				Matcher m = Pattern.compile("(#.+)").matcher(current);
				if (m.find()) {
					setComment(findKey(contentList, content.nextIndex() - 1), m.group());
				}
				
			} else if (current.matches("([\\s\\S]*#[\\s\\S]*)")) {
				String comment = current;
				while (content.hasNext() && contentList.get(content.nextIndex()).matches("([\\s\\S]*#[\\s\\S]*)")) {
					comment = comment.concat("\n").concat(content.next()); 
				}
				setComment(findKey(contentList, content.nextIndex()), comment);
			}
			
		}
		
		super.loadFromString(contents);
		if (super.options().header() != null) super.options().header("");
		
	}
	
	protected static final String removeTabsAndSpaces(String input) {
		return input.replaceAll(" ", "").replaceAll("\t", "");
	}
	
	protected static final int calculateTabsOrSpaces(String input) {
		if (!(input.startsWith(" ") 
				|| input.startsWith("\t"))) {
			return 0;
		}
		
		Matcher m = Pattern.compile("\\s+").matcher(input);
		return m.find() ? m.group().length() : 0;
	}
	
	protected static boolean areTabsUsed(String input) {
		return calculateTabsOrSpaces(input) == 0 ? false : input.startsWith("\t");
	}
	
	protected static String findKey(List<String> content, int index) {
		Pattern keyPattern = Pattern.compile("\\b.*(?=:)");
		List<String> reverseKey = new ArrayList<String>();
		
		ListIterator<String> iter = content.listIterator(index);
		
		if (!iter.hasNext()) return "";
		
		int tabsOrSpaces = -1;
		String current = iter.next();
		
		Matcher m = keyPattern.matcher(removeTabsAndSpaces(current));
		while ((!m.find()) && 
				(iter.hasNext())) {
			current = iter.next();
			m = keyPattern.matcher(removeTabsAndSpaces(current));
		}
		reverseKey.add(m.group());
		tabsOrSpaces = calculateTabsOrSpaces(current);
		
		while (iter.hasPrevious()) {
			if (tabsOrSpaces == 0) break;
			
			current = iter.previous();
			
			if ((calculateTabsOrSpaces(current) < tabsOrSpaces)
					&& !current.contains("#")) {
				
				m = keyPattern.matcher(removeTabsAndSpaces(current));
				if (m.find()) {
					
					reverseKey.add(m.group());
					tabsOrSpaces = calculateTabsOrSpaces(current);
					
				}
			}
		}
		return Joiner.on(".").join(Lists.reverse(reverseKey));
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
	
	@SuppressWarnings("unchecked")
	public SexyConfiguration update(SexyConfiguration file) throws FileNotFoundException, IOException, InvalidConfigurationException {
		for (String key : file.getKeys(true)) {
			if (!this.contains(key)) {
				this.set(key, file.get(key));
				this.addDefault(key, file.get(key));
			}
		}
		
		this.comments.clear();
		this.comments = (HashMap<String, String>) file.comments.clone();
		
		return this;
	}
}
