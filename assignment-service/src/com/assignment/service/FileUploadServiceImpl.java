package com.assignment.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.assignment.master.MasterDaoTemplate;
import com.assingment.dto.Deal_DTO;
import com.assingment.entity.Deal;
import com.assingment.entity.DealFile;
@Transactional
@Service("fileUploadService")
public class FileUploadServiceImpl<E,PK extends Serializable>  implements FileUploadService<E, Serializable>{
	
	 
	
	@Autowired
	MasterDaoTemplate<E, Serializable> daoTemp;

	@Override
	public void insertFileDetails(DealFile file) {
		StringBuffer mySql = new StringBuffer("INSERT INTO DEAL_FILE (ID, FILE_NAME) VALUES('").append(file.getId()).append("','").append(file.getFile_name()).append("')");
		daoTemp.insterByQuery(mySql.toString());
	}


	@Override
	public Map<String, Long> getAccumulativeCountList() {
		
		Map<String, Long> returnMap=new HashMap<>();
		List<Map<String, Object>> dbList=daoTemp.selectAll("select Ordering_Currency,Ordering_Currency_COUNT from deal_accumulative_count");
		for(Map map:dbList){
			returnMap.put((String)map.get("Ordering_Currency"), (Long)map.get("Ordering_Currency_COUNT"));
		}
		return returnMap;
	}

	
	@Override
	public void insertAccumulativeCount(List<String> cumulavtivCountList) {
		
		String querys=AccumulativeCountgetQuery(cumulavtivCountList,this.getAccumulativeCountList());
		
		deletAccumulativeCountData();
		
		daoTemp.insterByQuery(querys);
		
	}
	
	public static String AccumulativeCountgetQuery(List<String> cumulavtivCountList,Map<String, Long> dbMap) {
		
		Map<String, Long> countMap=new HashMap<>();

		Map<String, Long> inputMap = cumulavtivCountList.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		
		inputMap.forEach(new BiConsumer<String, Long>() {

			@Override
			public void accept(String key, Long value) {
				 
				Long check=dbMap.get(key);
				if(check!=null && check>0  ){
					countMap.put(key, (check+value));
				}else{
					countMap.put(key, value );
				}
			}
		});
		
		StringBuffer mySql = new StringBuffer("INSERT INTO  deal_accumulative_count (Ordering_Currency, Ordering_Currency_COUNT) VALUES ");
		for (Map.Entry<String, Long> map : countMap.entrySet()) {

			mySql = mySql.append("('").append(map.getKey()).append("',").append(map.getValue()).append("),");
		}

		return mySql.substring(0, mySql.length() - 1);
	}


	@Override
	public boolean checkExistFiel(String fileName) {
		StringBuffer mySql = new StringBuffer("select COUNT(FILE_NAME) from deal_file where FILE_NAME=").append("'").append(fileName).append("'");
		return daoTemp.checkExistData(mySql.toString());
	}


	@Override
	public void deletAccumulativeCountData() {
		daoTemp.deleteByQuery("DELETE FROM  deal_accumulative_count");
	}
	
	 
	public static String getQuery(String table,final List<Deal> lst) {
		String replaceStr="[^a-zA-Z0-9,://>]+";
		StringBuffer mySql = new StringBuffer("INSERT INTO ").append(table).append(" (DEAL_ID, FROM_CURRENCY_ISO_CODE, TO_CURRENCY_ISO_CODE, TIME_STAMP,AMOUT_ORDERING_CURRENCY,FILE_ID) VALUES ");
		for (int j = 0; j < lst.size(); j++) {
			mySql = mySql.append("(").append(lst.get(j).getDeal_id().replaceAll(replaceStr, "")).append(",'")
					.append(lst.get(j).getFrom_currency_iso_code().replaceAll(replaceStr, "")).append("','")
					.append(lst.get(j).getTo_currency_iso_code().replaceAll(replaceStr, "")).append("','").append(lst.get(j).getTime_stamp().replaceAll(replaceStr, ""))
					.append("',").append(lst.get(j).getAmount_ordering_currency().replaceAll(replaceStr, "")).append(",'")
					.append(lst.get(j).getfile_id().replaceAll(replaceStr, "")).append("' ),");
		}

		return mySql.substring(0, mySql.length() - 1);//replaceAll("null", "''");
	}
	@Transactional
	@Override
	public void InsertFileData(String tableName, List<Deal> list) {
		daoTemp.insterByQuery(getQuery(tableName,list));
		
	}


	@Override
	public List<Map<String, Object>> getAllRecords(String tableName) {
	 
		StringBuffer mySql = new StringBuffer(" select deal_id,from_currency_iso_code,to_currency_iso_code,time_stamp,amout_ordering_currency,file_id from ").append(tableName);
		
	 return	 daoTemp.selectAll(mySql.toString());
 
		 
	}


	@Override
	public int validCount() {
		 
		return daoTemp.selectCount("select count(1) from valid_deal");
	}


	@Override
	public int invalidCount() {
		 
		  return daoTemp.selectCount("select count(*) from invalid_deal");
	}


	@Override
	public Map<String,String> dealFiles() {
				 
		Map<String, String> returnMap=new HashMap<>();
		List<Map<String, Object>> dbList=daoTemp.selectAll("select id,file_name from deal_file");
		for(Map map:dbList){
			returnMap.put(map.get("id").toString(), map.get("file_name").toString());
		}
		return returnMap;
		 
	}


	 
 
}
