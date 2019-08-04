package me.alchemi.al.objects.handling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.alchemi.al.objects.base.PluginBase;

public class UpdateChecker {

	protected PluginBase plugin;
	protected Thread checker;
	
	public UpdateChecker(PluginBase plugin) {
		
		this.plugin = plugin;
		
		checker = new Thread(new TRunnable(false));
		checker.start();
	}
	
	public void check() {
	
		checker = new Thread(new TRunnable(true));
		checker.start();
		
	}
	
	protected void notifyServer(boolean update, boolean admins) {
		checker = null;
		
		if (!update) {
			plugin.getMessenger().print("&6Your copy of &o" + plugin.getDescription().getName() + "&6 is the most recent one.");
			
			if (admins) {
			
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (!player.hasPermission(Bukkit.getServer().getPluginManager().getPermission("al.notify"))) continue;
					plugin.getMessenger().sendMessage("&6Your copy of &o" + plugin.getDescription().getName() + "&6 is the most recent one.", player);
				}
				
			}
			
		} else {
			plugin.getMessenger().print("&6There's an update available for &o" + plugin.getDescription().getName()
					+ "\n&6Download it at &ohttps://www.spigotmc.org/resources/" + plugin.SPIGOT_ID + "/&r");
			
			if (admins) {
				
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (!player.hasPermission(Bukkit.getServer().getPluginManager().getPermission("al.notify"))) continue;
					plugin.getMessenger().sendMessage("&6There's an update available for &o" + plugin.getDescription().getName()
							+ "\n&6Download it at &ohttps://www.spigotmc.org/resources/" + plugin.SPIGOT_ID, player);
				}
				
			}
			
			
		}
	}
	
	protected class TRunnable implements Runnable{
		private boolean admins;
		
		public TRunnable(boolean admins) {
			this.admins = admins;
		}

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
					if (Integer.valueOf(br.readLine().replaceAll("\\D", "")) <= Integer.valueOf(plugin.getDescription().getVersion().replaceAll("\\D", ""))) {
						notifyServer(false, admins);
					} else {
						notifyServer(true, admins);
					}
				} catch (NumberFormatException e) {}
				
			} catch (IOException e) {} catch (NullPointerException e) {
				plugin.getMessenger().print("&cSPIGOT ID isn't initialized!");
			} 
			
		}
	}
	
}
