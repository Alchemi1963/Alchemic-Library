package me.alchemi.al.database.statementbuilder;

import me.alchemi.al.database.DataType;

public class AlterTableBuilder extends StatementBuilder {
	
	public AlterTableBuilder(StatementBuilder statement) {
		this.statement = statement.getStatement();
	}
	
	public final AlterTableBuilder change_column(String column) {
		statement = statement.concat("CHANGE COLUMN " + column + " ");
		return this;
	}
	
	public final AlterTableBuilder column(String column, DataType datatype, String... modifiers) {
		statement = statement.concat(column + " " + datatype.toString() + "(" + datatype.getMaxlength() + ")" + String.join(" ", modifiers) + ") ");
		return this;
	}
	
	public final AlterTableBuilder add_column(String column, DataType datatype, String... modifiers) {
		statement = statement.concat("ADD COLUMN " + column + " " + datatype.toString() + "(" + datatype.getMaxlength() + ")" + String.join(" ", modifiers) + ") ");
		return this;
	}
	
	public final AlterTableBuilder after(String column) {
		statement = statement.concat("AFTER " + column + " ");
		return this;
	}
	
	public final AlterTableBuilder drop_column(String column) {
		statement = statement.concat("DROP COLUMN " + column + " ");
		return this;
	}
	
	public final AlterTableBuilder rename(String table) {
		statement = statement.concat("RENAME TO " + table + " ");
		return this;
	}

}
