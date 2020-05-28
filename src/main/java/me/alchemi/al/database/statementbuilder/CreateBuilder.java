package me.alchemi.al.database.statementbuilder;

import java.util.Map;
import java.util.Map.Entry;

import me.alchemi.al.database.DataType;
import me.alchemi.al.database.Table;

public class CreateBuilder extends StatementBuilder{

	public CreateBuilder(StatementBuilder statement) {
		this.statement = statement.getStatement();
	}
	
	public final CreateBuilder database(String database) {
		statement = statement.concat("DATABASE " + database);
		return this;
	}
	
	public final CreateBuilder table(String table) {
		statement = statement.concat("TABLE " + table + " (");
		return this;
	}
	
	public final CreateBuilder ifnotexistsTable(String table) {
		statement = statement.concat("TABLE IF NOT EXISTS " + table + " (");
		return this;
	}
	
	public final CreateBuilder table(Table table) {
		statement = statement.concat("TABLE " + table.getDeclaration());
		return this;
	}
	
	public final CreateBuilder ifnotexiststable(Table table) {
		statement = statement.concat("TABLE IF NOT EXISTS " + table.getDeclaration());
		return this;
	}
	
	public final CreateBuilder ifnotexistsDatabase(String database) {
		statement = statement.concat("DATABASE IF NOT EXISTS " + database);
		return this;
	}
	
	private final CreateBuilder columnWrapper(String column, DataType datatype, String... modifiers) {
		
		if (statement.endsWith("(")) {
			statement = statement.concat(column + " " + datatype.toString() + "(" + datatype.getMaxlength() + ") " + String.join(" ", modifiers));
		} else {
			statement = statement.concat(", " + column + " " + datatype.toString() + "(" + datatype.getMaxlength() + ")" + String.join(" ", modifiers));
		}
		
		return this;
	}
	
	public final CreateBuilder columns(Map<String, DataType> columns, Map<String, String[]> modifiers) {
		
		for (Entry<String, DataType> column : columns.entrySet()) {
			if (modifiers.containsKey(column.getKey())) columnWrapper(column.getKey(), column.getValue(), modifiers.get(column.getKey()));
			else columnWrapper(column.getKey(), column.getValue()); 
		}
		statement = statement.concat(") ");
		return this;
	}
	
	public final CreateBuilder columns(Map<String, DataType> columns) {
		
		for (Entry<String, DataType> column : columns.entrySet()) {
			columnWrapper(column.getKey(), column.getValue());
		}
		statement = statement.concat(") ");
		return this;
	}
	
	public final CreateBuilder column(String column, DataType datatype, String...modifiers) {
		statement = statement.concat(column + " " + datatype.toString() + "(" + datatype.getMaxlength() + ")"  + String.join(" ", modifiers) + ") ");
		return this;
	}	
}
