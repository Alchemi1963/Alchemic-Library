package me.alchemi.al.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.alchemi.al.database.Column;
import me.alchemi.al.database.IDatabase;
import me.alchemi.al.database.Table;
import me.alchemi.al.database.statementbuilder.StatementBuilder;
import me.alchemi.al.objects.Callback;
import me.alchemi.al.objects.base.PluginBase;

public class MySQLDatabase implements IDatabase {

	private static boolean driverAvailable;
	
	private Connection connection;
	private final String user;
	private final String password;
	private final String url;
	
	private List<Table> tables;
	
	private final PluginBase plugin;
	
	public MySQLDatabase(PluginBase plugin, String address, String database, String user, String password) {
		this.plugin = plugin;
		this.user = user;
		this.password = password;
		if (!address.matches(".*:\\d+")) {
			address = address.concat(":3306");
		}
		this.url = "jdbc:mysql://%address%/%database%"
				.replace("%address%", address)
				.replace("%database%", database);
		
		if (!driverAvailable) return;
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public  void onDisable() {
		try {
			if (connection != null && !connection.isClosed()) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			
			executeUpdate(new StatementBuilder()
					.insert(table.getName())
					.column(column.getName())
					.values(value)
					.build());
			
		}
		
		return true;
	}
	
	/* Execute an update.
	 * Use this to modify the database.
	 */
	public void executeUpdate(String sql) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				try {
					connection.prepareStatement(sql).executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}.runTaskAsynchronously(plugin);
	}

	/* Execute a query.
	 * Use this to get values from the database.
	 * 
	 * @return the ResultSet from the query or null on errors.
	 */
	public void executeQuery(String sql, Callback<ResultSet> resultCall) {
		new BukkitRunnable() {
			
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
		}.runTaskAsynchronously(plugin);
	}

	/**
	 * @return the driverAvailable
	 */
	public static boolean isDriverAvailable() {
		return driverAvailable;
	}

	/**
	 * @return the connection
	 */
	@Override
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the url
	 */
	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public List<Table> getTables() {
		return tables;
	}

	@Override
	public boolean updateValue(Table table, Column column, Object oldValue, Object newValue, @Nullable Map<Column, Object> conditionalValues) {
		
		if (tables.contains(table) && table.hasColumn(column) && column.testObject(newValue)) {
			
			executeUpdate(new StatementBuilder()
					.update()
					.table(table.getName())
					.set(column.getName(), newValue)
					.where(column.getName(), oldValue)
					.build());
			
		}
		
		return true;
	}

	@Override
	public boolean removeRow(Table table, @NotNull Map<Column, Object> conditionalValues) {
		
		if (!tables.contains(table)) return false;
		
		Map<String, Object> conditions = new HashMap<String, Object>();
		
		for (Entry<Column, Object> entry : conditionalValues.entrySet()) {
			if (!table.hasColumn(entry.getKey())) return false;
			conditions.put(entry.getKey().getName(), entry.getValue());
		}
		
		executeUpdate(new StatementBuilder()
					.delete(table.getName())
					.wheres(conditions)
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
		
		return null;
	}

	@Override
	public void getValues(Callback<ResultSet> callback) {
		
		for (Table t : tables) {
			executeQuery(new StatementBuilder()
					.select(t.getName())
					.build(), callback);
		}
		
	}

	@Override
	public boolean doesTableExist(String table) {
		return tables.stream().anyMatch(Table -> Table.getName().equals(table));
	}

	@Override
	public void removeTable(Table table) {
		if (!tables.contains(table)) return;
		
		executeUpdate("DROP TABLE IF EXISTS " + table.getName() + ";");
	}

	@Override
	public Table getTable(String table) {
		if (!doesTableExist(table)) return null;
		
		List<Table> availableTables = tables.stream().filter(Table -> Table.getName().equals(table)).collect(Collectors.toList());
		
		return availableTables.get(0);
	}	
}
