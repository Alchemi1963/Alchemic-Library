package me.alchemi.al.database.statementbuilder;

import java.util.Map;
import java.util.Map.Entry;

public class UpdateBuilder extends StatementBuilder {

	public UpdateBuilder(StatementBuilder builder) {
		this.statement = builder.getStatement();
	}
	
	public final UpdateBuilder table(String table) {
		statement = statement.concat(table + " SET ");
		return this;
	}
	
	public final UpdateBuilder ignoreTable(String table) {
		statement = statement.concat("IGNORE " + table + " SET ");
		return this;
	}
	
	public final UpdateBuilder sets(Map<String, Object> columnVals) {
		
		for (Entry<String, Object> value : columnVals.entrySet()) {
			setWrapper(value.getKey(), value.getValue());
		}
		this.statement = this.statement.replaceFirst("(,$)", " ");
		
		return this;
		
	}
	
	private final UpdateBuilder setWrapper(String column, Object value) {
		statement = statement.concat(column + "=" + String.valueOf(value) + ",");
		return this;
	}
	
	public final UpdateBuilder set(String column, Object value) {
		statement = statement.concat(column + "=" + String.valueOf(value) + " ");
		return this;
	}
	
	public final UpdateBuilder wheres(Map<String, Object> statements) {
		
		for (Entry<String, Object> value : statements.entrySet()) {
			whereWrapper(value.getKey(), value.getValue());
		}
		statement = statement.concat(") ");
		return this;
	}
	
	private final UpdateBuilder whereWrapper(String column, Object value) {
		if (!statement.contains("WHERE")) statement = statement.concat("WHERE (" + column + "=" + String.valueOf(value));
		else statement = statement.concat(" AND "+ column + "=" + String.valueOf(value));
		return this;
	}
	
	public final UpdateBuilder where(String column, Object value) {
		statement = statement.concat("WHERE " + column + "=" + String.valueOf(value));
		return this;
	}
	
	public final UpdateBuilder condition(String condition) {
		if (condition.contains("WHERE")) statement = statement.concat(condition);
		else statement = statement.concat("WHERE " + condition);
		return this;
	}
}
