package me.alchemi.al.objects.meta;

import java.util.ListIterator;

import org.bukkit.metadata.MetadataValueAdapter;
import org.jetbrains.annotations.Nullable;

import me.alchemi.al.Library;
import net.md_5.bungee.api.chat.TextComponent;


public class ChatPagesMeta extends MetadataValueAdapter {

	public static final String KEY = "chat_pages";
	
	private final ListIterator<TextComponent> pages;
	private final boolean goNext;
	
	public ChatPagesMeta(ListIterator<TextComponent> pages) {
		this(pages, true);
	}
	
	public ChatPagesMeta(ListIterator<TextComponent> pages, boolean goNext) {
		super(Library.getInstance());
		this.pages = pages;
		this.goNext = goNext;
	}
	
	public boolean goNext() {
		return goNext;
	}
	
	public ListIterator<TextComponent> getPages(){
		return pages;
	}

	@Override
	public @Nullable Object value() {
		return pages;
	}

	@Override
	public void invalidate() {}
	
}
