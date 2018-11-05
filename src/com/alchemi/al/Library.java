package com.alchemi.al;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Library extends JavaPlugin{
	public static Library instance;
	
  public void onEnable() {
    System.out.println("Hello Stonehenge!");
    instance = this;
    
  }
  
  public void onDisable()
  {
    System.out.println("I don't wanna go...");
    saveConfig();
  }
  
  public static YamlConfiguration loadExtraConfig(File file) {
	  
	  YamlConfiguration conf = new YamlConfiguration();
	  
	  try {
			conf.load(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (InvalidConfigurationException ex) {
			ex.printStackTrace();
		}
	  
	  return conf;
	  
  }
  
  public static void saveExtraConfig(File file, FileConfiguration conf) {
	  try {
			conf.save(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
  }
  
  public static String fromIndex(String str, int index)
  {
    String newStr = str.substring(index);
    return newStr;
  }
  
  public static String cc(String msg)
  {
    String translated_message = ChatColor.translateAlternateColorCodes('&', msg);
    return translated_message;
  }
  
  public static String strVars(String msg, Player object, Player sender)
  {
    if (msg.contains("$player$")) {
      msg = msg.replace("$player$", object.getDisplayName());
    }
    if (msg.contains("$sender$")) {
      msg = msg.replace("$sender$", sender.getDisplayName());
    }
    return msg;
  }
  
  public static boolean containsAny(String string, String regex) {
	  for (char c : regex.toCharArray()) {
		  if (string.contains(String.valueOf(c))) return true;
	  }
	  
	  return false;
  }
  
  public static void print(Object msg, String pluginname)
  {
    System.out.println("[" + pluginname + "] " + cc(msg.toString()));
  }
  
  public static void broadcast(String msg, String pluginname) {
	  
	  instance.getServer().broadcastMessage("[" + pluginname + "] " + cc(msg));
	  
  }
  
  public static void sendMsg(String msg, Player reciever, Player sender)
  {
	  if (sender != null) msg = "[$sender$] --> &o[you] &r" + msg;
	  reciever.sendMessage(cc(strVars(msg, reciever, sender)));
  }
  
  public static boolean checkCmdPermission(Command cmd, CommandSender sender, String permission, String command)
  {
    if (cmd.getName().equalsIgnoreCase(command) && (sender.hasPermission(permission)) || cmd.getName().equalsIgnoreCase(command) && sender.isOp()) {
      return true;
    } if (!sender.hasPermission(permission))
    {
      Player player = (Player)sender;
      sendMsg("&7&oYou don't have permission to use &6" + cmd.getName() + "&7if you believe this is an error, &nplease contact an administrator&r&7.", player, null);
      
    }
    return false;
  }
}
