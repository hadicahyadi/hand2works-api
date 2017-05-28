package com.curiouslabs.hand2works.dao.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.curiouslabs.hand2works.annotation.AdditionalField;
import com.curiouslabs.hand2works.dao.GenericDao;
import com.curiouslabs.hand2works.model.BaseObject;
import com.google.common.base.CaseFormat;
import com.google.gson.Gson;

public class GenericDaoImpl<T extends BaseObject> implements GenericDao<T> {

	private Sql2o sql2o;
	private String tableName;
	private Class<T> clazz;
	protected StringBuilder insertSql;
	protected StringBuilder updateSql;
	private final String deleteSql;
	
	private StringBuilder columns;

	public GenericDaoImpl(Class<T> clazz, String tableName, Sql2o sql2o) {
		this.clazz = clazz;
		this.tableName = tableName;
		this.sql2o = sql2o;
		
		columns = new StringBuilder();
		deleteSql = "DELETE from "+tableName+" where id = ?";
		
		/**
		 * Get all the modifier fields and transform from camel case to lower underscore
		 */
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(AdditionalField.class)) {
				columns.append(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName())).append(",");
			}
		}
		// remove last comma on columns
		columns = new StringBuilder(columns.toString().substring(0,columns.length()-1));
		// build insert query
		insertSql = new StringBuilder()
				.append("INSERT into " + tableName)
				.append("(")
				.append(columns.toString())
				.append(")");
	}

	@Override
	public List<T> getAll() {
		String sql = "SELECT * from "+tableName;
		try (Connection con = sql2o.open()) {
			
			return con.createQuery(sql).setAutoDeriveColumnNames(true).executeAndFetch(clazz);
		}
	}

	@Override
	public int getRowCount() {
		String sql = "SELECT count(*) from " + tableName;
		try (Connection con = sql2o.open()) {
			return con.createQuery(sql).executeScalar(Integer.class);
		}
	}

	@Override
	public T save(T object) {
		try (Connection con = sql2o.open()) {
		    Long key = con.createQuery(insertSql.toString(), true).bind(object).executeUpdate().getKey(Long.class);
		    object.setId(key);
		}
		
		return object;
	}

}
