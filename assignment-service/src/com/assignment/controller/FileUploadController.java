package com.assignment.controller;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.master.MasterDaoTemplate;
import com.assignment.service.FileUploadService;
import com.assingment.dto.Deal_DTO;
import com.assingment.entity.Deal;
import com.assingment.entity.DealFile;
import com.assingment.entity.TotalCount;
import com.opencsv.CSVReader;

@RestController
public class FileUploadController<E extends Serializable> {

	public static Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	FileUploadService<E, Serializable> fileUploadService;

	

	@Autowired
	MasterDaoTemplate<E, Serializable> service;

	@RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getFileUpload(@RequestParam(value = "file") MultipartFile file) {

		long startTime = System.currentTimeMillis();
		List<Deal> insertList = new ArrayList<Deal>();
		List<Deal> invalid = new ArrayList<Deal>();
		List<String> cumulavtivCountList = new ArrayList<>();
		long timestamp = System.currentTimeMillis();
		long TotalTime = 0;

		String regx = "^[0-3]?[1-9]/[0-3]?[1-9]/(?:[0-9]{2})?[0-9]{2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";
		Pattern patt = Pattern.compile(regx);

		if (!fileUploadService.checkExistFiel(file.getOriginalFilename())) {
			try {

				@SuppressWarnings("resource")
				List<String[]> myEntries = new CSVReader(new InputStreamReader(file.getInputStream())).readAll();
				myEntries.remove(0);
				  
					Deal dto = null;
					for (String[] p : myEntries) {
						boolean b = true;
						dto = new Deal();
						try {

							if (p[0] != null && p[0].trim().length() > 0) {
								dto.setDeal_id(p[0]);
							} else {
								b = false;
								dto.setDeal_id(p[0]);
							}
							if (p[1] != null && p[1].trim().length() > 0) {
								dto.setFrom_currency_iso_code(p[1]);
								cumulavtivCountList.add(p[1]);
							} else {
								b = false;
								dto.setFrom_currency_iso_code(p[1]);
							}

							if (p[2] != null && p[2].trim().length() > 0) {
								dto.setTo_currency_iso_code(p[2]);
							} else {
								b = false;
								dto.setTo_currency_iso_code(p[2]);
							}
							if ((p[3] != null && p[3].trim().length() > 0) && patt.matcher(p[3].trim()).find()) {
								dto.setTime_stamp(p[3]);
							} else {
								b = false;
								dto.setTime_stamp(p[3].length() == 0 ? "''" : p[3]);
							}
							if (p[4] != null && p[4].trim().length() > 0) { //
								dto.setAmount_ordering_currency(p[4]);
							} else {
								b = false;
								dto.setAmount_ordering_currency(p[4].trim().length() == 0 ? "''" : p[4]);
							}

							if (b) {
								dto.setfile_id(String.valueOf(timestamp));
								insertList.add(dto);
							} else {
								dto.setfile_id(String.valueOf(timestamp));
								invalid.add(dto);
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

					ExecutorService executorService = Executors.newFixedThreadPool(30);

					Set<Callable<String>> callables = new HashSet<Callable<String>>();

					callables.add(new Callable<String>() {

						public String call() throws Exception {

							if (insertList != null && insertList.size() > 0) {
								fileUploadService.InsertFileData("VALID_DEAL", insertList);
							}
							return "VALID_DEAL";
						}
					});
					callables.add(new Callable<String>() {
						public String call() throws Exception {

							if (invalid != null && invalid.size() > 0) {
								fileUploadService.InsertFileData("INVALID_DEAL", invalid);
							}
							return "INVALID_DEAL";
						}
					});
					callables.add(new Callable<String>() {
						public String call() throws Exception {
							DealFile modal = new DealFile(String.valueOf(timestamp), file.getOriginalFilename());
							fileUploadService.insertFileDetails(modal);
							return "SAVE_FILE";
						}
					});
					callables.add(new Callable<String>() {
						public String call() throws Exception {
							if (cumulavtivCountList != null && cumulavtivCountList.size() > 0) {
								fileUploadService.insertAccumulativeCount(cumulavtivCountList);
							}
							return "SAVE_COUNT";
						}
					});
					executorService.invokeAll(callables);
					executorService.shutdown();

					long EndTime = System.currentTimeMillis();
					TotalTime = EndTime - startTime;
					System.out.println(TotalTime);

				/*} /*else {
					return new ResponseEntity<String>(String.valueOf(666666), HttpStatus.OK);
				}*/

			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<String>(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(TotalTime)),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(String.valueOf(555555), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getallrecord", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Deal_DTO>> getAllRecord() {

		List<Map<String, Object>> valid = new ArrayList<>();
		List<Map<String, Object>> invalid = new ArrayList<>();
		List<Deal_DTO> returnList = new ArrayList<>();
		List<DealFile> dealFiles = new ArrayList<>();
		Map<String, String> checkMap = new HashMap<>();

		try {
			valid = fileUploadService.getAllRecords("valid_deal");
			invalid = fileUploadService.getAllRecords("invalid_deal");
			checkMap = fileUploadService.dealFiles();
			valid.addAll(invalid);

			Deal_DTO dto = null;
			for (Map<?, ?> map : valid) {
				dto = new Deal_DTO();

				String fileName = checkMap.get(map.get("file_id"));
				dto.setFileName(fileName != null ? fileName : "");
				dto.setDeal_id(map.get("deal_id").toString());
				dto.setFrom_currency_iso_code(map.get("from_currency_iso_code").toString());
				dto.setTo_currency_iso_code(map.get("from_currency_iso_code").toString());
				dto.setTime_stamp(map.get("time_stamp").toString());
				dto.setAmount_ordering_currency(map.get("amout_ordering_currency").toString());
				returnList.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			return new ResponseEntity<List<Deal_DTO>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Deal_DTO>>(returnList, HttpStatus.OK);

	}

	@RequestMapping(value = "/getallrecordcount", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<TotalCount> getAllRecordCount() {

		TotalCount returnDto = new TotalCount();
		try {
			int valid = fileUploadService.validCount();
			int invalid = fileUploadService.invalidCount();

			ExecutorService executorService = Executors.newFixedThreadPool(40);

			Set<Callable<String>> callables = new HashSet<Callable<String>>();

			callables.add(new Callable<String>() {

				public String call() throws Exception {

					returnDto.setValidCount(valid);
					return "task1";
				}
			});
			callables.add(new Callable<String>() {
				public String call() throws Exception {
					returnDto.setInvalidCount(invalid);

					return "task2";
				}
			});

			executorService.invokeAll(callables);
			executorService.shutdown();

			returnDto.setTotalCount(valid + invalid);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ResponseEntity<TotalCount>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<TotalCount>(returnDto, HttpStatus.OK);

	}

}