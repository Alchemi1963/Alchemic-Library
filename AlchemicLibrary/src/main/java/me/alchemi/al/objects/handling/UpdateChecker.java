package me.alchemi.al.objects.handling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import me.alchemi.al.objects.base.PluginBase;

public class UpdateChecker implements Listener {

	protected PluginBase plugin;
	protected Thread checker;
	
	private boolean updateAvailable = false;
	
	public UpdateChecker(PluginBase plugin) {
		
		this.plugin = plugin;
		
		checker = new Thread(runner);
		checker.start();
	}
	
	public void check() {
	
		checker = new Thread(runner);
		checker.start();
		
	}
	
	protected void notifyServer() {
		if (updateAvailable) plugin.getMessenger().print("&6There's an update available for &o" + plugin.getDescription().getName()
				+ "\n&6Download it at &ohttps://www.spigotmc.org/resources/" + plugin.SPIGOT_ID + "/&r");
	}	
	
	protected void notifyAdmin(Player admin) {
		if (updateAvailable) plugin.getMessenger().sendMessage("&6There's an update available for &o" + plugin.getDescription().getName()
				+ "\n&6Download it at &ohttps://www.spigotmc.org/resources/" + plugin.SPIGOT_ID + "/&r", admin);
	}
	
	@EventHandler
	protected void onAdminLogin(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("al.checkupdate")) {
			notifyAdmin(e.getPlayer());
		}
	}
	
	@EventHandler
	protected void onCommand(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equals("/version " + plugin.getName())) {
			check();
			notifyAdmin(e.getPlayer());
		}
	}
	
	protected final Runnable runner = new Runnable() {
		
		@Override
		public void run() {
			
			URL url = null;
			URLConnection conn = null;
			
			try {
				
				if (plugin.SPIGOT_ID == 0) return;
				
				url = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + plugin.SPIGOT_ID);
				conn = url.openConnection();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				try {
					if (Integer.valueOf(br.readLine().replaceAll("\\D", "")) > Integer.valueOf(plugin.getDescription().getVersion().replaceAll("\\D", ""))) {
						updateAvailable = true;
						notifyServer();
					}
				} catch (NumberFormatException e) {}
				
			} catch (IOException e) {} catch (NullPointerException e) {
				plugin.getMessenger().print("&cSPIGOT ID isn't initialized!");
			} 
			
			checker = null;
		}
	};
}
