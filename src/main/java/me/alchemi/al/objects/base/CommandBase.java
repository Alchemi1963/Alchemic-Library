package me.alchemi.al.objects.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.alchemi.al.configurations.Messenger;

public abstract class CommandBase implements CommandExecutor {

	protected final Messenger messenger;
	private final String noPerm;
	private final String usage;
	
	public CommandBase(Messenger messenger, String permissionMessage, String usage) {
		this.messenger = messenger;
		this.noPerm = permissionMessage;
		this.usage = usage;
	}
	
	public CommandBase(PluginBase plugin, String permissionMessage, String usage) {
		this.messenger = plugin.getMessenger();
		this.noPerm = permissionMessage;
		this.usage = usage;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return true;
	}
	
	protected final void sendNoPermission(CommandSender sender, Command command) {
		messenger.sendMessage(noPerm.replace("%command%", command.getName()).replace("%permission%", command.getPermission()), sender);
	}
	
	protected final void sendUsage(CommandSender sender, Command command) {
		messenger.sendMessage(this.usage.replace("%usage%", command.getUsage()), sender);
	}
	
	protected String compileArgs(String[] args) {
		String string = "";
		for (String s : args) {
			string = string.concat(" " + s);
		}
		return string.trim();
	}
}
