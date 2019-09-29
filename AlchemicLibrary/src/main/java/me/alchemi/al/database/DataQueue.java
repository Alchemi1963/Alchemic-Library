package me.alchemi.al.database;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.bukkit.scheduler.BukkitRunnable;

import me.alchemi.al.Library;

public class DataQueue {

	Set<BukkitRunnable> tasks = new HashSet<BukkitRunnable>();
	BukkitRunnable scheduler;
	
	private static DataQueue queue;
	
	private DataQueue() {
		
		scheduler = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				tasks.forEach(new Consumer<BukkitRunnable>() {
					@Override
					public void accept(BukkitRunnable t) {
						
						t.run();
					}
				});
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
	
}
