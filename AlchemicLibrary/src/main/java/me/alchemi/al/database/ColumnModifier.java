package me.alchemi.al.database;

public enum ColumnModifier {

	NOT_NULL("NOT NULL"),
	DEFAULT("DEFAULT"),
	AUTO_INCREMENT("AUTO_INCREMENT");
	
	private String name;
	
	private ColumnModifier(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
