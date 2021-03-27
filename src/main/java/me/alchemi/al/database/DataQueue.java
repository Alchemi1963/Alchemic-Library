package me.alchemi.al.database;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.scheduler.BukkitRunnable;

import me.alchemi.al.Library;

public class DataQueue {

	private Set<BukkitRunnable> tasks = new HashSet<BukkitRunnable>();
	private BukkitRunnable scheduler;
	
	private static DataQueue queue;
	
	private DataQueue() {
		
		scheduler = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				Set<BukkitRunnable> set = ((HashSet)((HashSet)tasks).clone());
				tasks.clear();
				set.forEach(BukkitRunnable::run);
				
			}
		}; 
		scheduler.runTaskTimerAsynchronously(Library.getInstance(), 10, 200);
		
	}
	
	public static DataQueue getQueue() {
		if (queue == null) queue = new DataQueue();
		return queue;
	}
	
	public void add(BukkitRunnable task) {
		tasks.add(task);
	}
	
	public void runTasks() {
		scheduler.cancel();
		scheduler.run();
		scheduler.runTaskTimerAsynchronously(Library.getInstance(), 10, 200);
	}
	
	public Set<BukkitRunnable> getTasks() {
		return tasks;
	}
	
	public BukkitRunnable getScheduler() {
		return scheduler;
	}
	
}
