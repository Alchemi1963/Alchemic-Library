package me.alchemi.al.objects.handling;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import me.alchemi.al.Library;
import me.alchemi.al.configurations.Messenger;
import me.alchemi.al.objects.ChatListener;
import me.alchemi.al.objects.GUI.BookGUI;
import me.alchemi.al.objects.GUI.SignGUI;
import net.wesjd.anvilgui.AnvilGUI;

public class InputRequest {

	public enum RequestOption{
		ANVIL, CHAT, BOOK, SIGN;
	}
	
	public InputRequest(JavaPlugin plugin, Player object, RequestOption preferred_option, String quest) {
		
		switch(preferred_option) {
		case ANVIL:
			new AnvilGUI(plugin, object, Messenger.formatString(quest), (player, reply) -> { 
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
		private final RequestOption preferred_option;
		
		public InputReceivedEvent(String input, Player object, RequestOption preferred_option) {
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
		public RequestOption getPreferred_option() {
			return preferred_option;
		}
		
		
		
	}
}
