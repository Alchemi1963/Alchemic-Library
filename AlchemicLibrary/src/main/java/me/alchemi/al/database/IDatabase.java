package me.alchemi.al.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.alchemi.al.objects.Callback;

public interface IDatabase {

	public void createTable(Table table);
	
	public boolean insertValue(Table table, Column column, Object value);
	
	public boolean updateValue(Table table, Column column, Object oldValue, Object newValue, @Nullable Map<Column, Object> conditionalValues);
	
	public boolean removeRow(Table table, @NotNull Map<Column, Object> conditionalValues);
	
	public Column removeColumn(Table table, String column);
	
	public Table removeTable(String table);
	
	public void removeTable(Table table);
	
	public Table getTable(String table);
	
	public void getValues(Callback<ResultSet> callback);
	
	public boolean doesTableExist(String table);
	
	public Connection getConnection();
	
	public String getUrl();
	
	public List<Table> getTables();
	
}
