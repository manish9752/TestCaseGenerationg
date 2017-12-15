package com.assignment.master;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.assingment.entity.Deal;

public interface MasterDaoTemplate<E, PK extends Serializable> {

	public boolean checkExistData(String file_name);

	public void deleteByQuery(String query);

	public List<Map<String, Object>> selectAll(String query);

	public void insterByQuery(String sql);

	public List<E> selectAllData(String query);

	public int selectCount(String query);

}
