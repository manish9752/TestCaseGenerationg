package com.assignment.master;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.assingment.entity.Deal;

@Repository
public class MasterDaoTemplateImpl<E, PK extends Serializable> implements MasterDaoTemplate<E, PK> {

	@Autowired
	JdbcTemplate mysqlJdbctemplate;

	 

	 

	 

	@Override
	public boolean checkExistData(String query) {
		return mysqlJdbctemplate.queryForObject(query, Integer.class) == 1 ? true : false;

	}

	@Transactional
	@Override
	public void deleteByQuery(String query) {
		mysqlJdbctemplate.execute(query);

	}

	@Override
	public List<Map<String, Object>> selectAll(String query) {
		return mysqlJdbctemplate.queryForList(query);
	}

	@Override
	public void insterByQuery(String sql) {
		mysqlJdbctemplate.execute(sql);

	}


	@Override
	public List<E> selectAllData(String query) {
		return (List<E>) mysqlJdbctemplate.queryForList(query);

	}

	@Override
	public int selectCount(String query) {
		return mysqlJdbctemplate.queryForObject(query.toString(), Integer.class);
	}

	 

}
