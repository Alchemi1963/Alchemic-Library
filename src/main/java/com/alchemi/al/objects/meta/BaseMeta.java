package com.alchemi.al.objects.meta;

import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;

import com.alchemi.al.Library;

public abstract class BaseMeta extends MetadataValueAdapter{

	protected final Object value;
	
	protected BaseMeta(Plugin owningPlugin, Object value) {
		super(owningPlugin);
		
		this.value = value;
	}
	
	protected BaseMeta() {
		super(Library.instance);
		value = null;
	}
	
	@Override
	public Object value() {
		return value;
	}

	public abstract String name();
	
}
