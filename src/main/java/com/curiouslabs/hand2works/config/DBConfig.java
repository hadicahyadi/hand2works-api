package com.curiouslabs.hand2works.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

@Configuration
public class DBConfig {
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUsername("root");
		ds.setPassword("");
		ds.setUrl("jdbc:mysql://localhost/hand2works");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		
		return ds;
	}
	
	@Bean
	public Sql2o sql2o() {
		Sql2o sql = new Sql2o("jdbc:mysql://localhost/hand2works","root","");
		return sql;
	}

}
