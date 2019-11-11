package me.alchemi.al.objects;

import java.util.Map;

public class AlchemicEntry<K, V> implements Map.Entry<K, V>{

	private final K key;
	private V value;
	
	public AlchemicEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public AlchemicEntry(K key) {
		this.key = key;
		this.value = null;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}

}
