package me.alchemi.al.objects.placeholder;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class PapiParser implements Parser{

	@Override
	public String parse(OfflinePlayer player, String string) {
		string = PlaceholderAPI.setPlaceholders(player, string);
		return PlaceholderAPI.setBracketPlaceholders(player, string);
	}
	
	@Override
	public String parse(Player player, String string) {
		string = PlaceholderAPI.setPlaceholders(player, string);
		return PlaceholderAPI.setBracketPlaceholders(player, string);
	}
	
	@Override
	public String parse(CommandSender sender, String string) {
		if (sender instanceof Player) {
			return parse((Player)sender, string);
		} else {
			return string;
		}
	}
	
}
