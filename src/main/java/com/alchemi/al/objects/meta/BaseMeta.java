package com.alchemi.al.objects.meta;

import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;

public abstract class BaseMeta extends MetadataValueAdapter{

	protected final Object value;
	
	protected BaseMeta(Plugin owningPlugin, Object value) {
		super(owningPlugin);
		
		this.value = value;
	}
	
	@Override
	public void invalidate() {}
	
	@Override
	public Object value() {
		return value;
	}
	
}
