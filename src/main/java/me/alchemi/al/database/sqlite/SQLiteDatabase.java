package me.alchemi.al.database.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import me.alchemi.al.database.Column;
import me.alchemi.al.database.DataLog;
import me.alchemi.al.database.DataQueue;
import me.alchemi.al.database.IDatabase;
import me.alchemi.al.database.Table;
import me.alchemi.al.database.statementbuilder.StatementBuilder;
import me.alchemi.al.objects.Callback;
import me.alchemi.al.objects.StringSerializable;
import me.alchemi.al.objects.base.PluginBase;

public class SQLiteDatabase implements IDatabase {

	private static boolean driverAvailable;
	
	private final File file;
	private final String url;
	private Connection connection;
	private boolean initialized = false;
	
	private DataLog log;
	
	private List<Table> tables;
	
	private final PluginBase plugin;
	
	private SQLiteDatabase(PluginBase plugin, File dbFile, Connection connection) {
		this.file = dbFile;
		this.plugin = plugin;
		this.connection = connection;
		this.url = "jdbc:sqlite:" + dbFile;
		this.tables = new ArrayList<Table>();
		
		log = new DataLog(plugin);
		log.log("Established connection to " + url, Level.INFO);
	}
	
	public static SQLiteDatabase newConnection(PluginBase plugin, File dbFile) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
		return new SQLiteDatabase(plugin, dbFile, connection);
	}
	
	public static void load() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			driverAvailable = true;
		} catch (ClassNotFoundException e) {
			driverAvailable = false;			
			e.printStackTrace();
			System.err.println("jdbc driver unavailable");
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public static boolean isDriverAvailable() {
		return driverAvailable;
	}
	
	public boolean isInitialized() {
		return initialized;
	}
	
	@Override
	public void createTable(Table table) {
		tables.add(table);
		executeUpdate(new StatementBuilder()
				.create()
				.ifnotexiststable(table)
				.build());

	}

	@Override
	public boolean insertValue(Table table, Column column, Object value) {
		if (tables.contains(table) && table.hasColumn(column) && column.testObject(value)) {
			
			if (value instanceof String) value = "\"" + value + "\"";
			else if (value instanceof StringSerializable) value = "\"" + ((StringSerializable)value).serialize_string() + "\"";
			
			executeUpdate(new StatementBuilder()
					.insert(table.getName())
					.column(column.getName())
					.values(value)
					.build());
			return true;
			
		}
		
		return false;
	}
	
	@Override
	public boolean insertValueIgnore(Table table, Column column, Object value) {
		if (tables.contains(table) && table.hasColumn(column) && column.testObject(value)) {
			
			if (value instanceof String) value = "\"" + value + "\"";
			else if (value instanceof StringSerializable) value = "\"" + ((StringSerializable)value).serialize_string() + "\"";
			
			executeUpdate(new StatementBuilder()
					.insertIgnore(table.getName())
					.column(column.getName())
					.values(value)
					.build());
			return true;
			
		}
		
		return false;
	}

	@Override
	public boolean updateValue(Table table, Column column, Object newValue, Column conditionColumn, Object conditionValue) {
		
		if (tables.contains(table) && table.hasColumn(conditionColumn) && table.hasColumn(column) && column.testObject(newValue)) {
			
			if (newValue instanceof String) newValue = "\"" + newValue + "\"";
			else if (newValue instanceof StringSerializable) newValue = "\"" + ((StringSerializable)newValue).serialize_string() + "\"";
			
			executeUpdate(new StatementBuilder()
					.update()
					.table(table.getName())
					.set(column.getName(), newValue)
					.where(conditionColumn.getName(), conditionValue)
					.build());
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean updateValues(Table table, @NotNull Map<Column, Object> values, Column conditionColumn,
			Object conditionValue) {
		if (tables.contains(table) && table.hasColumn(conditionColumn)) {
			
			Map<String, Object> newValues = new HashMap<String, Object>();
			
			values.forEach((Column, Value) ->{
				if (Value instanceof String) Value = "\"" + Value + "\"";
				else if (Value instanceof StringSerializable) Value = "\"" + ((StringSerializable)Value).serialize_string() + "\"";
				
				if (table.hasColumn(Column) && Column.testObject(Value)) {
					newValues.put(Column.getName(), Value);
				}
			});
		
			if (newValues.isEmpty()) return false;
			
			executeUpdate(new StatementBuilder()
					.update()
					.table(table.getName())
					.sets(newValues)
					.where(conditionColumn.getName(), conditionValue)
					.build());
			return true;
		}
		
		return false;
	}

	@Override
	public boolean insertValues(Table table, @NotNull Map<Column, Object> values) {
		if (tables.contains(table)) {
			
			List<String> columnsList = new ArrayList<String>();
			for (Column c : values.keySet()) {
				if (table.hasColumn(c)) {
					columnsList.add(c.getName());
				}
			}
			String[] columns = columnsList.toArray(new String[columnsList.size()]);
			
			Object[] elements = values.entrySet().stream()
					.filter(Entry -> table.hasColumn(Entry.getKey()))
					.map(Entry ->{
						Object value = Entry.getValue();
						if (value instanceof String) value = "\"" + value + "\"";
						else if (value instanceof StringSerializable) value = "\"" + ((StringSerializable)value).serialize_string() + "\"";
						return value;
					})
					.toArray();
			
			executeUpdate(new StatementBuilder()
					.insert(table.getName())
					.columns(columns)
					.values(elements)
					.build());
			return true;
			
		}
		return false;
	}
	
	@Override
	public boolean insertValuesIgnore(Table table, @NotNull Map<Column, Object> values) {
		if (tables.contains(table)) {
			
			List<String> columnsList = new ArrayList<String>();
			for (Column c : values.keySet()) {
				if (table.hasColumn(c)) {
					columnsList.add(c.getName());
				}
			}
			String[] columns = columnsList.toArray(new String[columnsList.size()]);
			
			Object[] elements = values.entrySet().stream()
					.filter(Entry -> table.hasColumn(Entry.getKey()))
					.map(Entry ->{
						Object value = Entry.getValue();
						if (value instanceof String) value = "\"" + value + "\"";
						else if (value instanceof StringSerializable) value = "\"" + ((StringSerializable)value).serialize_string() + "\"";
						return value;
					})
					.toArray();
			
			executeUpdate(new StatementBuilder()
					.insertIgnore(table.getName())
					.columns(columns)
					.values(elements)
					.build());
			return true;
			
		}
		return false;
	}

	@Override
	public boolean removeRow(Table table, Column conditionColumn, Object conditionValue) {
		
		if (!(tables.contains(table) || table.hasColumn(conditionColumn))) return false;
		
		if (conditionValue instanceof String) conditionValue = "\"" + conditionValue + "\"";
		else if (conditionValue instanceof StringSerializable) conditionValue = "\"" + ((StringSerializable)conditionValue).serialize_string() + "\"";
		
		executeUpdate(new StatementBuilder()
					.delete(table.getName())
					.where(conditionColumn.getName(), conditionValue)
					.limit(1)
					.build());
		
		return true;
	}

	@Override
	public Column removeColumn(Table table, String column) {
		Column c = table.getColumn(column);
		
		if (c == null) return null;
		
		executeUpdate(new StatementBuilder()
				.altertable(table.getName())
				.drop_column(column)
				.build());
		
		return c;
	}

	@Override
	public Table removeTable(String table) {
		Table t = getTable(table);
		
		if (t == null) return null;
		
		executeUpdate("DROP TABLE IF EXISTS " + table + ";");
		
		return t;
	}

	@Override
	public void removeTable(Table table) {
		
		executeUpdate("DROP TABLE IF EXISTS " + table.getName() + ";");

	}

	@Override
	public Table getTable(String table) {
		if (!doesTableExist(table)) return null;
		
		List<Table> availableTables = tables.stream().filter(Table -> Table.getName().equals(table)).collect(Collectors.toList());
		
		return availableTables.get(0);
	}

	@Override
	public void getValuesAsync(Callback<ResultSet> callback) {
		
		for (Table t : tables) {
			executeQuery(new StatementBuilder()
					.select(t.getName())
					.build(), callback);
		}

	}

	@Override
	public void getValueAsync(Table table, Column column, Column conditionColumn, Object conditionValue,
			Callback<ResultSet> callback) {
		if (!(tables.contains(table) && table.hasColumn(column) && table.hasColumn(conditionColumn))) return;
		
		executeQuery(new StatementBuilder()
				.select(column.getName(), table.getName())
				.where(conditionColumn.getName(), conditionValue instanceof String ? "\"" + conditionValue + "\"" : conditionValue)
				.build(), callback);

	}
	
	@Override
	public void getValuesAsync(Table table, Column conditionColumn, Object conditionValue, Callback<ResultSet> callback, Column...columns) {
		
		if (!(tables.contains(table) && table.hasColumn(conditionColumn))) return;
		
		executeQuery(new StatementBuilder()
				.select(table.getName(), Arrays.asList(columns).stream().map(Column::getName).collect(Collectors.toSet()).toArray(new String[columns.length]))
				.where(conditionColumn.getName(), conditionValue instanceof String ? "\"" + conditionValue + "\"" : conditionValue)
				.build(), callback);
		
	}

	@Override
	public ResultSet getValue(Table table, Column column, Column conditionColumn, Object conditionValue)
			throws SQLException {
		if (!(tables.contains(table) && table.hasColumn(column) && table.hasColumn(conditionColumn))) return null;
		
		return connection.prepareStatement(new StatementBuilder()
				.select(column.getName(), table.getName())
				.where(conditionColumn.getName(), conditionValue instanceof String ? "\"" + conditionValue + "\"" : conditionValue)
				.build()).executeQuery();
	}
	
	@Override
	public ResultSet getValues(Table table, Column conditionColumn, Object conditionValue, Column...columns) throws SQLException {
		if (!(tables.contains(table) && table.hasColumn(conditionColumn))) return null;
		
		return connection.prepareStatement(new StatementBuilder()
				.select(table.getName(), Arrays.asList(columns).stream().map(Column::getName).collect(Collectors.toSet()).toArray(new String[columns.length]))
				.where(conditionColumn.getName(), conditionValue instanceof String ? "\"" + conditionValue + "\"" : conditionValue)
				.build()).executeQuery();
	}

	@Override
	public boolean doesTableExist(String table) {
		return tables.stream().anyMatch(Table -> Table.getName().equals(table));
	}
	
	/* Execute an update.
	 * Use this to modify the database.
	 */
	public void executeUpdate(String sql) {
		DataQueue.getQueue().add(new BukkitRunnable() {

			@Override
			public void run() {
				try {
					connection.prepareStatement(sql).executeUpdate();
					log.log(sql, Level.INFO);
				} catch (SQLException e) {
					e.printStackTrace();
					log.log(sql, Level.SEVERE);
				}
				
			}
		});
	}

	/* Execute a query.
	 * Use this to get values from the database.
	 * 
	 * @return the ResultSet from the query or null on errors.
	 */
	public void executeQuery(String sql, Callback<ResultSet> resultCall) {
		DataQueue.getQueue().add(new BukkitRunnable() {

			@Override
			public void run() {

				try {
					ResultSet result = connection.prepareStatement(sql).executeQuery();

					new BukkitRunnable() {

						@Override
						public void run() {

							resultCall.call(result);

						}
					}.runTask(plugin);

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public String getUrl() {
		return url; 
	}

	@Override
	public List<Table> getTables() {
		return tables;
	}

}
