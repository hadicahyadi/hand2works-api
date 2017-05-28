package com.curiouslabs.hand2works.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.curiouslabs.hand2works.dao.BrandDao;
import com.curiouslabs.hand2works.model.Brand;

@Repository
public class BrandDaoImpl extends GenericDaoImpl<Brand> implements BrandDao{

	@Autowired
	public BrandDaoImpl(Sql2o sql2o) {
		super(Brand.class, "m_brand", sql2o);
		insertSql.append(" values ( :brandName )");
	}
	
	
}
