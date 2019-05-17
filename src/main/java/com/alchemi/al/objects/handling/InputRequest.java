package com.alchemi.al.objects.handling;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.alchemi.al.Library;
import com.alchemi.al.configurations.Messenger;
import com.alchemi.al.objects.ChatListener;
import com.alchemi.al.objects.GUI.BookGUI;
import com.alchemi.al.objects.GUI.SignGUI;

import net.wesjd.anvilgui.AnvilGUI;

public class InputRequest {

	public enum REQUEST_OPTION{
		ANVIL, CHAT, BOOK, SIGN;
	}
	
	public InputRequest(JavaPlugin plugin, Player object, REQUEST_OPTION preferred_option, String quest) {
		
		switch(preferred_option) {
		case ANVIL:
			new AnvilGUI(plugin, object, Messenger.cc(quest), (player, reply) -> { 
				Bukkit.getPluginManager().callEvent(new InputReceivedEvent(reply, object, preferred_option));
				return null;
			});
			break;
		case BOOK:
			new BookGUI(plugin, object, (player, reply) -> { 
				Bukkit.getPluginManager().callEvent(new InputReceivedEvent(reply, object, preferred_option));
				return null;
			}, quest);
			break;
		case CHAT:
			new ChatListener(plugin, object, (player, reply) -> {
				Bukkit.getPluginManager().callEvent(new InputReceivedEvent(reply, player, preferred_option));
				return null;
			}, quest);
			break;
		case SIGN:
			new SignGUI(plugin, object, (player, reply) -> {
				Bukkit.getPluginManager().callEvent(new InputReceivedEvent(reply, player, preferred_option));
				return null;
			}, quest);
			break;
		}
		
	}
	
	
	public static class InputReceivedEvent extends Event{

		private static final HandlerList handlers = new HandlerList();
		
		private final String input;
		private final Player player;
		private final REQUEST_OPTION preferred_option;
		
		public InputReceivedEvent(String input, Player object, REQUEST_OPTION preferred_option) {
			this.input = input;
			this.player = object;
			this.preferred_option = preferred_option;
			
			object.sendMessage(input);
		}
		
		@Override
		public HandlerList getHandlers() {
			return handlers;
		}
		
		public static HandlerList getHandlerList(){
			return handlers;
		}

		/**
		 * @return the input
		 */
		public String getInput() {
			return input;
		}
		
		/**
		 * @return the input as int
		 */
		public int asInt() {
			return Library.testIfNumber(input) ? Integer.valueOf(input) : -1;
		}
		
		/**
		 * @return the input as double
		 */
		public double asDouble() {
			return Library.testIfNumber(input) ? Double.valueOf(input) : -1.0D;
		}
		
		/**
		 * @return the input as boolean
		 */
		public boolean asBoolean() {
			return Boolean.parseBoolean(input);
		}

		/**
		 * @return the player
		 */
		public Player getPlayer() {
			return player;
		}

		/**
		 * @return the preferred_option
		 */
		public REQUEST_OPTION getPreferred_option() {
			return preferred_option;
		}
		
		
		
	}
}
