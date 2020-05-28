package me.alchemi.al.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import me.alchemi.al.objects.Callback;
import me.alchemi.al.objects.StringSerializable;

public interface IDatabase {

	void createTable(Table table);
	
	boolean insertValue(Table table, Column column, Object value);
	
	boolean insertValueIgnore(Table table, Column column, Object value);
	
	boolean updateValue(Table table, Column column, Object newValue, Column conditionColumn, Object conditionValue);
	
	boolean updateValues(Table table, @NotNull Map<Column, Object> values, Column conditionColumn, Object conditionValue);
	
	boolean insertValues(Table table, @NotNull Map<Column, Object> values);

	boolean insertValuesIgnore(Table table, @NotNull Map<Column, Object> values);
	
	boolean removeRow(Table table, Column conditionColumn, Object conditionValue);
	
	Column removeColumn(Table table, String column);
	
	Table removeTable(String table);
	
	void removeTable(Table table);
	
	Table getTable(String table);
	
	void getValuesAsync(Callback<ResultSet> callback);
	
	void getValueAsync(Table table, Column column, Column conditionColumn, Object conditionValue, Callback<ResultSet> callback);
	
	ResultSet getValue(Table table, Column column, Column conditionColumn, Object conditionValue) throws SQLException;
	
	ResultSet getValues(Table table, Column conditionColumn, Object conditionValue, Column... columns) throws SQLException;
	
	boolean doesTableExist(String table);
	
	Connection getConnection();
	
	String getUrl();
	
	List<Table> getTables();

	void getValuesAsync(Table table, Column conditionColumn, Object conditionValue, Callback<ResultSet> callback, Column...columns);
	
	static String prepareValue(Object input) {
		if (input instanceof String
				&& !((String)input).matches(".+\\(.*\\)")) {
			return "\"" + input + "\"";
		} else if (input instanceof StringSerializable) {
			return "\"" + ((StringSerializable)input).serialize_string() + "\"";
		} else if (input instanceof UUID) {
			return "\"" + ((UUID)input).toString() + "\"";
		} else if (input instanceof Timestamp) {
			return ((Timestamp)input).toString();
		}
		return String.valueOf(input);
	}
	
}
