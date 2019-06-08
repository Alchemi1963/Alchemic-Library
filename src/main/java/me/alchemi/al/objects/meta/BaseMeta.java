package me.alchemi.al.objects.meta;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.metadata.MetadataValueAdapter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.alchemi.al.objects.base.PluginBase;

public abstract class BaseMeta extends MetadataValueAdapter{

	protected final Object value;
	
	public BaseMeta(Plugin owningPlugin, Object value) {
		super(owningPlugin);
		
		this.value = value;
	}
	
	@Override
	public void invalidate() {}
	
	@Override
	public Object value() {
		return value;
	}
	
	public Map<String, Object> serialize() {
		HashMap<String, Object> mapped = new HashMap<String, Object>();
		
		mapped.put("owner", this.getOwningPlugin().getName());
		mapped.put("value", value());
		
		return mapped;
	}
	
	public static BaseMeta deserialize(Map<String, Object> serializedObject) {
		if (serializedObject.containsKey("==")) {
			try {
				@SuppressWarnings("unchecked")
				Class<? extends BaseMeta> clazz = (Class<? extends BaseMeta>) Class.forName((String) serializedObject.get("=="));
				Constructor<?> cons = clazz.getDeclaredConstructors()[0];
				if (Arrays.asList(cons.getParameterTypes()).contains(JavaPlugin.class) || Arrays.asList(cons.getParameterTypes()).contains(PluginBase.class)) {
					return (BaseMeta) clazz.getDeclaredConstructors()[0].newInstance(Bukkit.getPluginManager().getPlugin((String) serializedObject.get("owner")), serializedObject.get("value"));
				}
				return (BaseMeta) clazz.getDeclaredConstructors()[0].newInstance(serializedObject.get("value"));
			} catch(IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException 
					| SecurityException | ClassNotFoundException e) {e.printStackTrace();
			}
		}
		return null;
	}
	
}
