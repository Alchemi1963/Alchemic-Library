package me.alchemi.al.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import me.alchemi.al.objects.Callback;

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
	
}
