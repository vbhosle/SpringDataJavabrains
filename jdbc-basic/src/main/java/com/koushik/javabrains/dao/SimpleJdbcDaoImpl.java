package com.koushik.javabrains.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SimpleJdbcDaoImpl extends JdbcDaoSupport {
	
	public int getCircleCount() {
		String sql = "select count(*) from circle";
		return this.getJdbcTemplate().queryForObject(sql, Integer.class);
	}
}
