package com.assingment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;
@Table(name="VALID_DEAL")
 public class Deal implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "DEAL_ID")
	private String deal_id;
	
	@Column(name = "FROM_CURRENCY_ISO_CODE")
	private String from_currency_iso_code;
	
	@Column(name = "TO_CURRENCY_ISO_CODE")
	private String to_currency_iso_code;
	
	@Column(name = "TIME_STAMP")
	private String time_stamp;
	
	@Column(name = "AMOUT_ORDERING_CURRENCY")
	private String amount_ordering_currency;
	
	
	@Column(name = "FILE_ID")
	private String file_id;
	

	public String getDeal_id() {
		return deal_id;
	}

	public void setDeal_id(String deal_id) {
		this.deal_id = deal_id;
	}

	public String getFrom_currency_iso_code() {
		return from_currency_iso_code;
	}

	public void setFrom_currency_iso_code(String from_currency_iso_code) {
		this.from_currency_iso_code = from_currency_iso_code;
	}

	public String getTo_currency_iso_code() {
		return to_currency_iso_code;
	}

	public void setTo_currency_iso_code(String to_currency_iso_code) {
		this.to_currency_iso_code = to_currency_iso_code;
	}

	public String getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}

	public String getAmount_ordering_currency() {
		return amount_ordering_currency;
	}

	public void setAmount_ordering_currency(String amount_ordering_currency) {
		this.amount_ordering_currency = amount_ordering_currency;
	}

	public String getfile_id() {
		return file_id;
	}

	public void setfile_id(String file_id) {
		this.file_id = file_id;
	}

	public Deal(String deal_id, String from_currency_iso_code, String to_currency_iso_code, String time_stamp,
			String amount_ordering_currency, String file_id) {
		super();
		this.deal_id = deal_id;
		this.from_currency_iso_code = from_currency_iso_code;
		this.to_currency_iso_code = to_currency_iso_code;
		this.time_stamp = time_stamp;
		this.amount_ordering_currency = amount_ordering_currency;
		this.file_id = file_id;
	}

	public Deal() {
		super();
	}

	 
	
	 

}
 