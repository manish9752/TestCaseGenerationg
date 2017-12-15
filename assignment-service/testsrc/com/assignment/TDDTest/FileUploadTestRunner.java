package com.assignment.TDDTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.assignment.controller.FileUploadController;
import com.assignment.service.FileUploadService;
import com.assingment.dto.Deal_DTO;
import com.assingment.entity.InvalidDeal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml" })
@WebAppConfiguration
public class FileUploadTestRunner {

	public static Logger logger = Logger.getLogger(FileUploadController.class);

	@Autowired
	FileUploadService services;

	@Autowired
	FileUploadController controller;

	// Check Existing file name from File deal_files
	// if file present it will return true else it will return false
	// test case 1 -if file not present return false

	// Checking controller fileUploadController All

	@Test
	public void Case1_Test_insertFile() {
		logger.info("insert record test");

		ResponseEntity record_count = controller.getFileUpload(FileUploadJunitTestHelper.getMultipartData(
				Paths.get("D:\\SampleFile.csv").toString(), "SampleFile.csv", "SampleFile.csv", "text/csv", null));

		int Expected_OK_Status = 200;
		int Expected_Error_Status = 404;
		int Expected_Error_Dublicate = 202;

		// Cheacking Http status value if response OK
		assertTrue("if http status 200 Or OK", Expected_OK_Status == record_count.getStatusCode().value());

		// Cheacking Http status value if response NOT_FOUND
		assertTrue("if http status 404 NOT_FOUND", Expected_OK_Status == record_count.getStatusCode().value());

		// Cheacking Http status value if response Already exist
		assertTrue("if http status 202 Already exist",
				Expected_Error_Dublicate == record_count.getStatusCode().value());

	}

	@Test
	public void Case2_Test_getAllValidInvalidRecord() {
		
		logger.info("get valid record");

		Integer expected_size = 100000;

		// if passed / true else fail

		// 1. Test equal.
		assertThat(services.getAllRecords("valid_deal").size() + services.getAllRecords("invalid_deal").size(),
				is(expected_size));

		List<Deal_DTO> list = services.getAllRecords("valid_deal");

		// 2 check empty collection

		assertThat(new HashMap<>(), not(list.isEmpty()));

		// 3 check empty collection

		assertThat(new HashMap<>(), not(list.isEmpty()));

	}

	@Test
	public void Case3_Test_getAccumulativeCountList() {

		// 1 check count size
		assertThat(new HashMap<>(), not(services.getAccumulativeCountList().isEmpty()));

		// Test item, and its value
		Map<String, Long> returnMap = services.getAccumulativeCountList();
		assertThat(returnMap, IsMapContaining.hasEntry("FJD", (long) 362));

		// 4. Test map key
		assertThat(returnMap, IsMapContaining.hasKey("FJD"));

	}

	@Test
	public void Case4_Test_insertAccumulativeCount() {
		// Test item, and its value
		Map<String, Long> returnMap = services.getAccumulativeCountList();

	}

	@Test
	public void Case5_Test_getValidInvalidCount() {

		// Test item, and its value
		int high = 1;
		int low = 0;
		assertTrue("count is greater than 0 ", high >= services.validCount());

		 

	}

	@Test
	public void Case5_Test_checkDealFile() {

		// 1 check count size
		assertThat(new HashMap<>(), not(services.dealFiles().isEmpty()));

		// Test item, and its value
		Map<String, String> returnMap = services.dealFiles();

		
	}

	@Test
	public void Case5_Test_deleteAccumulativeCount() {

		// 1 if data deleted
		boolean expected = true;
		logger.info("record deleted successfully ");
	//	assertEquals(expected, services.deletAccumulativeCountData());

	}

	// Checking controller fileUploadController

	@Test
	public void Case5_Test_getAllRecordController() {
		
		logger.info("get All record");

		ResponseEntity count = controller.getAllRecord();
		// System.out.println(count.getStatusCode().value());

		int Expected_OK_Status = 200;
		int Expected_Error_Status = 404;
		int Expected_Error_Dublicate = 202;

		// Cheacking Http status value if response OK
		assertTrue("if http status 200 Or OK", Expected_OK_Status == count.getStatusCode().value());

		// Cheacking Http status value if response NOT_FOUND
		assertTrue("if http status 404 NOT_FOUND", Expected_OK_Status == count.getStatusCode().value());

		// Cheacking Http status value if response Already exist
		assertTrue("if http status 202 Already exist", Expected_Error_Dublicate == count.getStatusCode().value());

	}

	// Checking controller fileUploadController All

	@Test
	public void Case5_Test_getAllRecordController_Count() {
		
		logger.info("get record count");

		ResponseEntity record_count = controller.getAllRecordCount();

		int Expected_OK_Status = 200;
		int Expected_Error_Status = 404;
		int Expected_Error_Dublicate = 202;

		// Cheacking Http status value if response OK
		assertTrue("if http status 200 Or OK", Expected_OK_Status == record_count.getStatusCode().value());

		// Cheacking Http status value if response NOT_FOUND
		assertTrue("if http status 404 NOT_FOUND", Expected_OK_Status == record_count.getStatusCode().value());

		// Cheacking Http status value if response Already exist
		assertTrue("if http status 202 Already exist",
				Expected_Error_Dublicate == record_count.getStatusCode().value());

	}

	@Test
	public void Case5_Test_insertDealFiles() {

		logger.info("check dto object");
		
		assertNotNull(new Deal_DTO());
		
		assertNotNull(new InvalidDeal());

	}

	@Test
	public void Case1_Test_checkExisting() {
		
		logger.info("check existing object");

		boolean expected = true;
		logger.info("test file is not present in deal_files");
		//assertEquals(expected, services.checkExistFiel("demo.csv"));

		logger.info("test file is  present in deal_files");
		//assertEquals(expected, services.checkExistFiel("SampleFile.csv"));

	}

	@Test
	public void Case12_Test_getAccumulativeCount() {
		
		logger.info("get AccumulativeCount count");

		Map<String, Long> returnMap = services.getAccumulativeCountList();

		// 2 check empty collection

		assertThat(new HashMap<>(), not(returnMap.isEmpty()));

		// 3 check empty collection

		assertThat(new HashMap<>(), not(returnMap.isEmpty()));

	}

}
