package com.alchemi.al.sexyconfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class SexyConfiguration extends YamlConfiguration {

	private HashMap<Integer, String> comments = new HashMap<Integer, String>(); //line, comment
	private HashMap<String, Integer> keys = new HashMap<String, Integer>();     //key, line
	
	private ArrayList<String> source = new ArrayList<String>();
	
	@SuppressWarnings("unused") //line 61 - ugh
	@Override
	public String saveToString() {
		
		String data = "";
		
		if (source.isEmpty()) {
			Bukkit.getLogger().log(Level.WARNING, "Source not set! Use setSource to set it!");
			return super.saveToString();
		}
		
		for (String key : keys.keySet()) {
			
			
			
			if (!(getValues(true).get(key) instanceof MemorySection) && !isConfigurationSection(key)) {
				
				String value = "";
				if (get(key) instanceof List<?>) {
					
					for (Object v : getList(key)) {
						String indentation = "\n";
						for (String ugh : key.split("[.]")) indentation = indentation.concat("  ");
						indentation = indentation.concat("- ");
						value = value.concat(indentation + String.valueOf(v));
					}
					
				} else value = String.valueOf(getValues(true).get(key));
				
				value = value.concat("\n");
				source.set(keys.get(key), source.get(keys.get(key)).concat(" " + value));
				
			}
			
		}
		
		for (String line : source) {
			int index = source.indexOf(line);
			if (comments.containsKey(index)) {
				
				Pattern pat = Pattern.compile("\\w");
				Matcher mat = pat.matcher(line);
				
				line = line.concat("\n" + (mat.find() ? line.substring(0, mat.start()) : "")  + line.split("^\\S")[0] + comments.get(index));
				source.set(index, line);
			}
			if (data.equals("")) data = line;
			else data = data.concat("\n" + line);
		}
		System.out.println(data);
		return data;
	}
	
	public void setSource(InputStream input) {
		Validate.notNull(input, "Source cannot be null!");
		
		Scanner scanner = new Scanner(input);
		String sc = scanner.useDelimiter("\\A").next();
		scanner.close();
		
		ArrayList<String> l = new ArrayList<String>();
		
		for (String line : Arrays.asList(sc.split("\n"))) {
			
			if (line.contains("#")) {
				comments.put(l.size() - 1, line.substring(line.indexOf("#")));
				if (!line.substring(0, line.indexOf("#")).contains(":") && !line.substring(0, line.indexOf("#")).contains("-")) {
					line = line.substring(0, line.indexOf("#")) + "\n";
					continue;
				}
				line = line.substring(0, line.indexOf("#"));
			}
			if (line.contains(":")) {
				line = line.substring(0, line.indexOf(":") + 1);
			}
			if (line.contains(" - ")) {
				continue;
			}
			
			l.add(line);
		}
		
		source = l;
	}
	
	@Override
	public void load(File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");

        final FileInputStream stream = new FileInputStream(file);

        load(new InputStreamReader(stream, Charsets.UTF_8));
	}
	
	@Override
	public void load(Reader reader) throws IOException, InvalidConfigurationException {
		BufferedReader input = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);

        StringBuilder builder = new StringBuilder();

        try {
            String line;

            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } finally {
            input.close();
        }

        loadFromString(builder.toString());
        parseLines(builder.toString());
	}
	
	@Override
	public void loadFromString(String content) throws InvalidConfigurationException {
		ArrayList<String> contents = new ArrayList<String>();
		contents.addAll(Arrays.asList(content.split("\n")));
		
		/*for (String s : contents) {
			
			if (s.contains("#")) {
				
				int i = s.indexOf("#");
				comments.put(contents.indexOf(s), s.substring(i));
				
			}
			
		}*/
		
		super.loadFromString(content);

	}
	
	public void parseLines(String contents) {
		ArrayList<String> content = new ArrayList<String>();
		for (String coke : Arrays.asList(contents.split("\n"))) {
			//coke.snort("A LOT");
			if (coke.contains(":")) content.add(coke);
		}
		
		parseLines(content);
	}
	
	public void parseLines(ArrayList<String> contents) {
		
		
		Set<String> keyset = getKeys(true);
		
		for (String s : keyset) {
			
			List<String> key = Arrays.asList(s.split("[.]"));
			String indentation = "  ";
			int current_indent = 0;
			int total_indent = key.size();
			
			
			for (String k : key) {
				
				for (String line : contents) {
					
					
					if (line.substring(0, line.contains(":") ? line.indexOf(":") : line.length()).replaceAll(" ", "").equals(k)) {
						indentation.concat("  ");
						current_indent ++;
						if (current_indent == total_indent) {
							keys.put(s, contents.indexOf(line));
						}
						
						break;
					}
				}
			}
		}
	}

	@Override
	protected String buildHeader() {
		return null;
	}
	
	@Override
	public void save(String file) throws IOException {

		Validate.notNull(file, "File cannot be null");
		
		save(new File(file));
	}
	
	@Override
	public void save(File file) throws IOException {
		Validate.notNull(file, "File cannot be null");

        Files.createParentDirs(file);

        String data = saveToString();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);

        try {
            writer.write(data);
        } finally {
            writer.close();
        }
	}
	
	public static SexyConfiguration loadConfiguration(File file) {
		
		SexyConfiguration config = new SexyConfiguration();
		try {
			config.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration!", e);
		}
		
		return config;
		
	}
	

}
