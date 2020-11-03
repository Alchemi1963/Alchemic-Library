package me.alchemi.al.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import me.alchemi.al.Library;
import me.alchemi.al.objects.base.PluginBase;
import me.alchemi.al.objects.handling.CarbonDating;

public class DataLog {

	private final File log;
	private final PluginBase plugin;
	
	protected static final File logsFolder = new File(Library.getInstance().getDataFolder(), "sql-logs");
	
	public DataLog(PluginBase plugin) {
		if (!logsFolder.exists()) logsFolder.mkdirs();
		
		this.plugin = plugin;
		this.log = new File(logsFolder, plugin.getName() + ".log");
		try {
			if (!log.exists()) log.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void log(String sql, Level level) {
		CarbonDating current = CarbonDating.getCurrentDateTime();
		
		sql = "[" + current.toString() + " " + level.toString() + "]: " + sql;
		
		try {
			FileWriter writer = new FileWriter(log);
			writer.append("\n" + sql);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getLog() {
		return log;
	}
	
	public PluginBase getPlugin() {
		return plugin;
	}
	
	public static File getLogsfolder() {
		return logsFolder;
	}
	
}
