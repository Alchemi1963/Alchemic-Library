package me.alchemi.al.database.statementbuilder;

import java.util.Map;
import java.util.Map.Entry;

public class SelectBuilder extends StatementBuilder {

	public SelectBuilder(StatementBuilder statement) {
		this.statement = statement.getStatement();
	}
	
	public final SelectBuilder wheres(Map<String, Object> statements) {
		
		for (Entry<String, Object> value : statements.entrySet()) {
			whereWrapper(value.getKey(), value.getValue());
		}
		statement = statement.concat(") ");
		return this;
	}
	
	private final SelectBuilder whereWrapper(String column, Object value) {
		if (!statement.contains("WHERE")) statement = statement.concat("WHERE (" + column + "=" + String.valueOf(value));
		else statement = statement.concat(" AND " + column + "=" + String.valueOf(value));
		return this;
	}
	
	public final SelectBuilder whereMulti(String column, String...values) {
		statement = statement.concat("WHERE (" + column + " IN (" + String.join(", ", values)) + "))";
		return this;
	}
	
	public final SelectBuilder where(String column, Object value) {
		statement = statement.concat("WHERE " + column + "=" + String.valueOf(value) + " ");
		return this;
	}
	
	public final SelectBuilder condition(String condition) {
		if (condition.contains("WHERE")) statement = statement.concat(condition);
		else statement = statement.concat("WHERE " + condition + " ");
		return this;
	}
	
	public final SelectBuilder groupby(String column) {
		statement = statement.concat("GROUP BY " + column + " ");
		return this;
	}
	
	public final SelectBuilder having(String conditions) {
		statement = statement.concat("HAVING " + conditions + " ");
		return this;
	}
	
	public final SelectBuilder orderby(String column) {
		statement = statement.concat("ORDER BY " + column + " ");
		return this;
	}
	
	public final SelectBuilder limit(int limit) {
		statement = statement.concat("LIMIT " + limit + " ");
		return this;
	}
}
