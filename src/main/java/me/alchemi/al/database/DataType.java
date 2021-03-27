package me.alchemi.al.database;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public enum DataType {
	CHAR(255),
	VARCHAR(255),
	TINYTEXT(255),
	TEXT(65535),
	MEDIUMTEXT(16777215),
	LONGTEXT(4294967295L),
	TINYINT(Byte.class),
	SMALLINT(Short.class),
	MEDIUMINT(-8388608,8388607),
	INT(Integer.class),
	BIGINT(Long.class),
	FLOAT(Float.class),
	REAL(Float.class),
	DOUBLE(Double.class),
	DECIMAL(String.class),
	DATE(Date.class),
	DATETIME(Timestamp.class),
	TIMESTAMP(Timestamp.class),
	TIME(Time.class),
	BOOLEAN(Boolean.class);
	
	private long maxlength;
	private long minlength;
	private final Class<?> clazz;
	
	private DataType(long maxlength) {
		clazz = String.class;
		minlength = 0;
		this.maxlength = maxlength;
	}
	
	private DataType(long minlength, long maxlength) {
		clazz = Integer.class;
		this.minlength = minlength;
		this.maxlength = maxlength;
	}
	
	private DataType(Class<?> datatype) {
		clazz = datatype;
		try {
			maxlength = datatype.getField("MAX_VALUE").getLong(null);
			minlength = datatype.getField("MIN_VALUE").getLong(null);
			
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException e) {
			
			maxlength = 0;
			minlength = 0;
			
		}
	}
	
	public long getMaxlength() {
		return maxlength;
	}
	
	public boolean testObject(Object o) {
		System.out.println(clazz.isInstance(o));
		return clazz.isInstance(o) && 
				(maxlength == minlength ||
				((o instanceof String && 
						o.toString().length() < maxlength)
						|| (o instanceof Number 
								&& ((Number)o).longValue() > minlength
								&& ((Number)o).longValue() < maxlength)));
	}
}
