package me.alchemi.al.objects.base;

import org.bukkit.plugin.java.JavaPlugin;

import me.alchemi.al.configurations.Messenger;

public abstract class PluginBase extends JavaPlugin {

	protected Messenger messenger;
	
	public int SPIGOT_ID;

	/**
	 * 
	 * @return the messenger
	 */
	public Messenger getMessenger() {
		return messenger;
	}
	
	/**
	 * @param messenger the messenger to set
	 */
	public void setMessenger(Messenger messenger) {
		this.messenger = messenger;
	}
}
