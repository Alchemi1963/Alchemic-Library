package me.alchemi.al.objects.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabCompleteBase implements TabCompleter {
    
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

		List<Object> list = new ArrayList<Object>();
		
		if (!(sender instanceof Player))
			return Arrays.asList("");
		
		
		return returnSortSuggest(list, args);
	}
	
	protected List<String> returnSortSuggest(List<Object> list, String[] args){
		
		List<String> tabSuggest = new ArrayList<>();
		
		for (Object o : list) {
			if (String.valueOf(o).toLowerCase().startsWith(args[args.length - 1].toLowerCase())) tabSuggest.add(String.valueOf(o));
		}
		
		Collections.sort(tabSuggest);
		return tabSuggest;
		
	}
	
}
