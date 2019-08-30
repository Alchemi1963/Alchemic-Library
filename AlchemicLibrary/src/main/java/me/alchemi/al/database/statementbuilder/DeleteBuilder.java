package me.alchemi.al.database.statementbuilder;

import java.util.Map;
import java.util.Map.Entry;

public class DeleteBuilder extends StatementBuilder {

	public DeleteBuilder(StatementBuilder statement) {
		this.statement = statement.getStatement();
	}
	
	public final DeleteBuilder wheres(Map<String, Object> statements) {
		
		for (Entry<String, Object> value : statements.entrySet()) {
			whereWrapper(value.getKey(), value.getValue());
		}
		
		return this;
	}
	
	private final DeleteBuilder whereWrapper(String column, Object value) {
		if (!statement.contains("WHERE")) statement = statement.concat("WHERE " + column + "=" + String.valueOf(value));
		else statement = statement.concat(" AND " + column + "=" + String.valueOf(value));
		return this;
	}
	
	public final DeleteBuilder where(String column, Object value) {
		statement = statement.concat("WHERE " + column + "=" + String.valueOf(value) + " ");
		return this;
	}
	
	public final DeleteBuilder condition(String condition) {
		if (condition.contains("WHERE")) statement = statement.concat(condition);
		else statement = statement.concat("WHERE " + condition);
		return this;
	}
	
	public final DeleteBuilder orderby(String column) {
		statement = statement.concat("ORDER BY " + column + " ");
		return this;
	}
	
	public final DeleteBuilder limit(int limit) {
		statement = statement.concat("LIMIT " + limit + " ");
		return this;
	}
	
}
