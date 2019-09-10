package me.alchemi.al.database;

import java.util.Arrays;
import java.util.UUID;

public class Column {

	private final String name;
	private final DataType datatype;
	private final ColumnModifier[] modifiers;
	private final UUID uuid;
	private Object defValue = null;
	
	public Column(String name, DataType datatype, ColumnModifier...columnModifiers) {
		this.name = name;
		this.datatype = datatype;
		this.modifiers = columnModifiers;
		this.uuid = UUID.randomUUID();
	}
	
	public void setDefValue(Object defValue) {
		if (Arrays.asList(modifiers).contains(ColumnModifier.DEFAULT)) this.defValue = defValue;
	}
	
	public boolean testObject(Object o) {
		return (o == null && Arrays.asList(modifiers).contains(ColumnModifier.NOT_NULL))
				|| datatype.testObject(o);
	}
	
	public String getName() {
		return name;
	}
	
	public DataType getDatatype() {
		return datatype;
	}

	public ColumnModifier[] getModifiers() {
		return modifiers;
	}
	
	public String getStringedModifiers() {
		String string = "";
		
		for (ColumnModifier mod : modifiers) {
			string = string.concat(mod.getName() + " ");
			if (mod == ColumnModifier.DEFAULT) string = string.concat(String.valueOf(defValue) + " ");
		}
		return string.trim();
	}
	
	public String getDeclaration() {
		return name + " " + datatype.toString() + " " + getStringedModifiers();
	}
	
	@Override
	public String toString() {
		return getStringedModifiers().isEmpty() 
				? "Column{name:" + name + ", datatype:" + datatype.name() + "}" 
						:"Column{name:" + name + ", datatype:" + datatype.name() + "modifiers:" + getStringedModifiers() + "}";
	}

	public UUID getUuid() {
		return uuid;
	}
}
