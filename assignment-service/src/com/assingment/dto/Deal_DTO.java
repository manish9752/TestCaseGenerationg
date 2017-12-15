package com.assingment.dto;

  public class Deal_DTO {
	private String deal_id;
	private String from_currency_iso_code;
	private String to_currency_iso_code;
	private String time_stamp;
	private String amount_ordering_currency;
	private String fileName;
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
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
	public Deal_DTO() {
		super();
	}
	public Deal_DTO(String deal_id, String from_currency_iso_code, String to_currency_iso_code, String time_stamp,
			String amount_ordering_currency, String fileName) {
		super();
		this.deal_id = deal_id;
		this.from_currency_iso_code = from_currency_iso_code;
		this.to_currency_iso_code = to_currency_iso_code;
		this.time_stamp = time_stamp;
		this.amount_ordering_currency = amount_ordering_currency;
		this.fileName = fileName;
	}
	 
	
	
	

}
