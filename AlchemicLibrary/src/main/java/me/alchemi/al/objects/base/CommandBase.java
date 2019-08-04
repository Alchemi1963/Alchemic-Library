package me.alchemi.al.objects.base;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.alchemi.al.configurations.Messenger;

public abstract class CommandBase implements CommandExecutor {

	protected final Messenger messenger;
	private final String noPerm;
	
	public CommandBase(Messenger messenger, String permissionMessage) {
		this.messenger = messenger;
		this.noPerm = permissionMessage;
	}
	
	public CommandBase(PluginBase plugin, String permissionMessage) {
		this.messenger = plugin.getMessenger();
		this.noPerm = permissionMessage;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return true;
	}
	
	protected final void sendNoPermission(CommandSender sender, Command command) {
		messenger.sendMessage(noPerm.replace("%command%", command.getName()).replace("%permission%", command.getPermission()), sender);
	}
	
	protected final String compileArgs(String[] args) {
		String string = "";
		for (String s : args) {
			string = string.concat(" " + s);
		}
		return string.trim();
	}
}
