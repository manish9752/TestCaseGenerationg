package com.assignment.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.assingment.dto.Deal_DTO;
import com.assingment.entity.Deal;
import com.assingment.entity.DealFile;

public interface FileUploadService<E, PK extends Serializable> {

	public void insertFileDetails(DealFile deal);

	public Map<String, Long> getAccumulativeCountList();

	public void insertAccumulativeCount(List<String> cumulavtivCountList);

	public boolean checkExistFiel(String fileName);

	public void deletAccumulativeCountData();

	public void InsertFileData(String tableName, List<Deal> list);

	public List<Map<String, Object>> getAllRecords(String name);
	
	public int validCount();
	
	public int invalidCount();
	
	public Map<String, String> dealFiles();
	
	 

}
