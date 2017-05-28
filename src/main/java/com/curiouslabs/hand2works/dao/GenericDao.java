package com.curiouslabs.hand2works.dao;

import java.util.List;

import com.curiouslabs.hand2works.model.BaseObject;

public interface GenericDao<T extends BaseObject> {
	
	public List<T> getAll();
	public int getRowCount();
	public T save(T object);

}
