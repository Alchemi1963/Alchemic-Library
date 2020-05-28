package me.alchemi.al.objects.placeholder;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alchemi.al.configurations.IMessage;

public interface IStringParser {

	IStringParser message(IMessage message);
	
	IStringParser player(Player player);
	
	IStringParser player(String player);
	
	IStringParser command(String command);
	
	IStringParser amount(int amount);
	
	IStringParser parse(Player player);
	
	IStringParser parse(OfflinePlayer player);
	
	IStringParser parse(CommandSender sender);
	
	String create();
	
}
