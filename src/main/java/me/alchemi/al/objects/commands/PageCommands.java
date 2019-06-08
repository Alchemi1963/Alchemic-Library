package me.alchemi.al.objects.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alchemi.al.Library;
import me.alchemi.al.objects.meta.ChatPagesMeta;
import me.alchemi.al.objects.meta.PersistentMeta;

public class PageCommands implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		
		Player player = (Player) sender;
		ChatPagesMeta meta = null;
		
		if (PersistentMeta.hasMeta(player, ChatPagesMeta.class)) {
			meta = (ChatPagesMeta) PersistentMeta.getMeta(player, ChatPagesMeta.class);
		} else {
			return true;
		}
		
		if (label.equals(Library.toBinary("next")) && meta.getPages().hasNext()) {
			
			if (!meta.goNext()) meta.getPages().next();
			player.spigot().sendMessage(meta.getPages().next());
			player.setMetadata(ChatPagesMeta.class.getName(), new ChatPagesMeta(meta.getPages(), true));
			
		} else if (label.equals(Library.toBinary("previous")) && meta.getPages().hasPrevious()) {
			
			if (meta.goNext()) meta.getPages().previous();
			player.spigot().sendMessage(meta.getPages().previous());
			player.setMetadata(ChatPagesMeta.class.getName(), new ChatPagesMeta(meta.getPages(), false));
			
		}
		
		
		
		return true;
	};

}
