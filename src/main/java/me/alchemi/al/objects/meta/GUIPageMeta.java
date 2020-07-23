package me.alchemi.al.objects.meta;

import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class GUIPageMeta extends MetadataValueAdapter {

	public static final String KEY = "gui_page";
	
	private final int guiPage;
	
	public GUIPageMeta(Plugin owningPlugin, int guiPage) {
		super(owningPlugin);
		this.guiPage = guiPage;
	}
	
	@Override
	public @Nullable Object value() {
		return guiPage;
	}
	
	@Override
	public void invalidate() {}
}
