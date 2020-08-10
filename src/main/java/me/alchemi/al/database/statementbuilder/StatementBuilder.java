package me.alchemi.al.database.statementbuilder;

public class StatementBuilder {

	protected String statement;
	
	public StatementBuilder() {
		statement = "";
	}
	
	public final CreateBuilder create() {
		statement = "CREATE ";
		return new CreateBuilder(this);
	}
	
	public final UpdateBuilder update() {
		statement = "UPDATE ";
		return new UpdateBuilder(this);
	}
	
	public final SelectBuilder select(String table, String...columns) {
		statement = "SELECT " + String.join(", ", columns) + " FROM " + table + " ";
		return new SelectBuilder(this);
	}
	
	public final SelectBuilder select(String column, String table) {
		statement = "SELECT " + column + " FROM " + table + " ";
		return new SelectBuilder(this);
	}

	public final SelectBuilder select(String table) {
		statement = "SELECT * FROM " + table + "";
		return new SelectBuilder(this);
	}
	
	public final InsertBuilder insert(String table) {
		statement = "INSERT INTO " + table + " ";
		return new InsertBuilder(this);
	}
	
	public final InsertBuilder insertIgnore(String table) {
		statement = "INSERT IGNORE INTO " + table + " ";
		return new InsertBuilder(this);
	}
	
	public final InsertBuilder insertIgnoreSQLite(String table) {
		statement = "INSERT OR IGNORE INTO " + table + " ";
		return new InsertBuilder(this);
	}
	
	public final DeleteBuilder delete(String table) {
		statement = "DELETE FROM " + table + " ";
		return new DeleteBuilder(this);
	}
	
	public final AlterTableBuilder altertable(String table) {
		statement = "ALTER TABLE" + table + " ";
		return new AlterTableBuilder(this);
	}
	
	public final String build() {
		return statement + ";";
	}
	
	protected final String getStatement() {
		return statement;
	}
	
}
