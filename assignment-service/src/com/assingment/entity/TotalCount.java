package com.assingment.entity;

public class TotalCount {
	
	private int validCount;
	private int invalidCount;
	private int totalCount;
	
	public int getValidCount() {
		return validCount;
	}
	public void setValidCount(int validCount) {
		this.validCount = validCount;
	}
	public int getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(int invalidCount) {
		this.invalidCount = invalidCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public TotalCount() {
		super();
	}
	public TotalCount(int validCount, int invalidCount, int totalCount) {
		super();
		this.validCount = validCount;
		this.invalidCount = invalidCount;
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "TotalCount [validCount=" + validCount + ", invalidCount=" + invalidCount + ", totalCount=" + totalCount
				+ "]";
	}
	
	
	

}
