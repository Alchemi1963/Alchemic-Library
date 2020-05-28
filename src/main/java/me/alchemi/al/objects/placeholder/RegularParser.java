package me.alchemi.al.objects.placeholder;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegularParser implements Parser{

	@Override
	public String parse(OfflinePlayer player, String string) {
		return string.replace("%player%", player.getName());
	}

	@Override
	public String parse(Player player, String string) {
		return string.replace("%player%", player.getName());
	}

	@Override
	public String parse(CommandSender sender, String string) {
		return string.replace("%player%", sender.getName());
	}
	
}
