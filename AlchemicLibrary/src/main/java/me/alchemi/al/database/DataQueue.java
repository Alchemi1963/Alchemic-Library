package me.alchemi.al.database;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.scheduler.BukkitRunnable;

import me.alchemi.al.Library;
import me.alchemi.al.configurations.Messenger;

public class DataQueue {

	private Set<BukkitRunnable> tasks = new HashSet<BukkitRunnable>();
	private BukkitRunnable scheduler;
	
	private static DataQueue queue;
	
	private DataQueue() {
		
		scheduler = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				Iterator<BukkitRunnable> iter = tasks.iterator();
				while (iter.hasNext()) {
					
					Messenger.printStatic("Running SQL task.");
					iter.next().run();
					
				}
				tasks.clear();
			}
		}; 
		scheduler.runTaskTimerAsynchronously(Library.getInstance(), 10, 20);
		
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
		scheduler.runTaskTimerAsynchronously(Library.getInstance(), 10, 20);
	}
	
	public Set<BukkitRunnable> getTasks() {
		return tasks;
	}
	
	public BukkitRunnable getScheduler() {
		return scheduler;
	}
	
}
