package me.alchemi.al.database;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Sets;

public class Table {

	private String name;
	private Set<Column> columns;
	private final UUID uuid;
	
	private Table(String name, Set<Column> columns, UUID uuid){
		this.name = name;
		this.columns = columns;
		this.uuid = uuid;
	}
	
	public Table(String name, Column firstColumn) {
		columns = Sets.newHashSet(firstColumn);
		
		this.name = name.replace("-", "_").replace(" ", "_");
		this.uuid = UUID.randomUUID();
	}
	
	public Table(String name, Column...columns) {
		this.columns = Sets.newHashSet(columns);
		
		this.name = name.replace("-", "_").replace(" ", "_");
		this.uuid = UUID.randomUUID();
	}
	
	/**
	 * Returns a new Table instance with the new name.
	 * 
	 * @param newName
	 * @return a new Table instance
	 * @apiNote You should create the table anew in the database.
	 */
	public Table rename(String newName) {
		return new Table(newName, columns, uuid);
	}
	
	public Set<Column> getColumns() {
		return columns;
	}
	
	public void addColumn(Column column) {
		columns.add(column);
	}
	
	public boolean hasColumn(Column column) {
		return columns.contains(column);
	}
	
	public Column getColumn(String column) {
		List<Column> availableColumns = columns.stream().filter(Column -> Column.getName().equals(column)).collect(Collectors.toList());
		
		if (availableColumns.isEmpty()) return null;
		return availableColumns.get(0);
	}
	
	public String getName() {
		return name;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public boolean dropColumn(Column column) {
		if (hasColumn(column)) {
			return columns.remove(column);
		}
		return false;
	}
	
	public Column change_column(Column column, @Nullable String name, @Nullable DataType datatype, @Nullable ColumnModifier...columnModifiers) {
		if(!dropColumn(column)) return column;
		
		if (name == null) name = column.getName();
		if (datatype == null) datatype = column.getDatatype();
		if (columnModifiers == null) columnModifiers = column.getModifiers();
		
		Column newColumn = new Column(name, datatype, columnModifiers);
		addColumn(newColumn);
		return newColumn;
	}
	
	public String getDeclaration() {
		return name + "(" + String.join(", ", columns.stream().map(Column::getDeclaration).collect(Collectors.toSet())) + ")";
	}
	
	@Override
	public String toString() {
		return "Table{name:" + name + "columns:" + columns.toString() + "}";
	}
}
