package me.alchemi.al.objects.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.alchemi.al.objects.meta.ChatPagesMeta;
import me.alchemi.al.objects.meta.MetaUtil;
import me.alchemi.al.util.NumUtil;

public class PageCommands implements Listener {
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
				
		Player player = e.getPlayer();
		ChatPagesMeta meta = null;
		
		if (MetaUtil.hasMeta(player, ChatPagesMeta.KEY, ChatPagesMeta.class)) {
			meta = MetaUtil.getMeta(player, ChatPagesMeta.KEY, ChatPagesMeta.class);
		} else {
			return;
		}
		
		if (e.getMessage().equals("/" + NumUtil.toBinary("next")) && meta.getPages().hasNext()) {
			
			if (!meta.goNext()) meta.getPages().next();
			player.spigot().sendMessage(meta.getPages().next());
			player.setMetadata(ChatPagesMeta.KEY, new ChatPagesMeta(meta.getPages(), true));
			e.setCancelled(true);
			
		} else if (e.getMessage().equals("/" + NumUtil.toBinary("previous")) && meta.getPages().hasPrevious()) {
			
			if (meta.goNext()) meta.getPages().previous();
			player.spigot().sendMessage(meta.getPages().previous());
			player.setMetadata(ChatPagesMeta.KEY, new ChatPagesMeta(meta.getPages(), false));
			e.setCancelled(true);
			
		}
	}

}
