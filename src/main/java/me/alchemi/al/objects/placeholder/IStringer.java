package me.alchemi.al.objects.placeholder;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.alchemi.al.objects.base.ConfigBase.IMessage;

public interface IStringer {

	IStringer message(IMessage message);
	
	IStringer player(Player player);
	
	IStringer player(String player);
	
	IStringer command(String command);
	
	IStringer amount(int amount);
	
	IStringer parse(Player player);
	
	IStringer parse(OfflinePlayer player);
	
	IStringer parse(CommandSender sender);
	
	String create();
	
}
