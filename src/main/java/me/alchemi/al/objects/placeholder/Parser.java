package me.alchemi.al.objects.placeholder;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface Parser {

	public String parse(OfflinePlayer player, String string);
	
	public String parse(Player player, String string);
	
	public String parse(CommandSender sender, String string);
	
}
