package me.alchemi.al.configurations;

public interface IMessage {

	void setValue(Object value);
	
	String value();
	
	String key();
	
	String toString();
}