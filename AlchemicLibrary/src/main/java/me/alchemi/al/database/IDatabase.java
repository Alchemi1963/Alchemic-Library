package me.alchemi.al.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.alchemi.al.objects.Callback;

public interface IDatabase {

	public void createTable(Table table);
	
	public boolean insertValue(Table table, Column column, Object value);
	
	public boolean insertValueIgnore(Table table, Column column, Object value);
	
	public boolean updateValue(Table table, Column column, Object newValue, @Nullable Map<Column, Object> conditionalValues);
	
	public boolean insertValues(Table table, @NotNull Map<Column, Object> values);

	public boolean insertValuesIgnore(Table table, @NotNull Map<Column, Object> values);
	
	public boolean removeRow(Table table, @NotNull Map<Column, Object> conditionalValues);
	
	public Column removeColumn(Table table, String column);
	
	public Table removeTable(String table);
	
	public void removeTable(Table table);
	
	public Table getTable(String table);
	
	public void getValuesAsync(Callback<ResultSet> callback);
	
	public void getValueAsync(Table table, Column column, Column conditionColumn, Object conditionValue, Callback<ResultSet> callback);
	
	public ResultSet getValue(Table table, Column column, Column conditionColumn, Object conditionValue) throws SQLException;
	
	public boolean doesTableExist(String table);
	
	public Connection getConnection();
	
	public String getUrl();
	
	public List<Table> getTables();
	
}
