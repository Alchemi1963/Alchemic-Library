package com.alchemi.al.objects.base;

import java.io.File;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.configurations.Messenger;
import com.alchemi.al.configurations.SexyConfiguration;

public abstract class PluginBase extends JavaPlugin {

	private Messenger messenger;
	
	private PluginBase instance;
	
	public HashMap<SexyConfiguration, ConfigIdentifier> configs;
	
	public int SPIGOT_ID;
	
	@Override
	public void onEnable() {
	
		instance = this;
		
		this.configs = new HashMap<SexyConfiguration, PluginBase.ConfigIdentifier>();
		
	}
	
	public PluginBase getInstance() {
		return instance;
	}

	public Messenger getMessenger() {
		return messenger;
	}
	
	/**
	 * @param messenger the messenger to set
	 */
	public void setMessenger(Messenger messenger) {
		this.messenger = messenger;
	}

	protected class ConfigIdentifier{
		
		final int version;
		final File file;
		
		public ConfigIdentifier(File file, int version) {
			this.file = file;
			this.version = version;
		}
	}

	
}
