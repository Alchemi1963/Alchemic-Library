package me.alchemi.al.util;

import java.util.function.BooleanSupplier;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ScheduleUtil {
	
	private int taskId;
	 
	public static ScheduleUtil getInstance() {
		return new ScheduleUtil();
	}
	
	public void scheduleRepeatingTask(Plugin plugin, Runnable task, long delay, long period, BooleanSupplier stopSupplier) {
		this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				task.run();
				if (stopSupplier.getAsBoolean()) {
					Bukkit.getScheduler().cancelTask(taskId);
				}
			}
			
		}, delay, period);
	}
	
	@SuppressWarnings("deprecation")
	public void scheduleAsyncRepeatingTask(Plugin plugin, Runnable task, long delay, long period, BooleanSupplier stopSupplier) {
		this.taskId = Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				task.run();
				if (stopSupplier.getAsBoolean()) {
					Bukkit.getScheduler().cancelTask(taskId);
				}
			}
			
		}, delay, period);
	}

}
