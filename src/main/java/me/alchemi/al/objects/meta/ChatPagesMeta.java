package me.alchemi.al.objects.meta;

import java.util.ListIterator;

import me.alchemi.al.Library;
import net.md_5.bungee.api.chat.TextComponent;


public class ChatPagesMeta extends BaseMeta {

	private ListIterator<TextComponent> pages;
	private boolean goNext = true;
	
	public ChatPagesMeta(ListIterator<TextComponent> pages) {
		super(Library.getInstance(), pages);
		this.pages = pages;
	}
	
	public ChatPagesMeta(ListIterator<TextComponent> pages, boolean goNext) {
		super(Library.getInstance(), pages);
		this.pages = pages;
		this.goNext = goNext;
	}
	
	public boolean goNext() {
		return goNext;
	}
	
	public ListIterator<TextComponent> getPages(){
		return pages;
	}
	
}
