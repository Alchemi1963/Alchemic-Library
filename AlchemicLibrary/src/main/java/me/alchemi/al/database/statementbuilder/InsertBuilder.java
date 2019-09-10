package me.alchemi.al.database.statementbuilder;

public class InsertBuilder extends StatementBuilder {
	
	public InsertBuilder(StatementBuilder statement) {
		this.statement = statement.getStatement();
	}
	
	public final InsertBuilder column(String column) {
		if (!statement.contains("(")) statement = statement.concat(" (" + column);
		else statement = statement.concat("," + column);
		return this;
	}
	
	public final InsertBuilder columns(String... columns) {
		
		for (String column : columns) {
			column(column);
		}
		statement = statement.concat(") ");
		return this;
	}
	
	private final InsertBuilder value(Object value) {		
		if (!statement.contains("VALUES (")) statement = statement.concat("VALUES (" + String.valueOf(value));
		else if (statement.endsWith(")") && statement.contains("VALUES ")) statement = statement.concat(",(" + String.valueOf(value));
		else statement = statement.concat("," + String.valueOf(value));
		return this;
	}
	
	public final InsertBuilder values(Object... values) {
		
		for (Object value : values) {
			value(value);
		}
		statement = statement.concat(")");
		return this;
	}
}
