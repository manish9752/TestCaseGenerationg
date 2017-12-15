package com.assingment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DEAL_FILE")
public class DealFile implements Serializable {
	
	public DealFile(String id, String file_name) {
		super();
		this.id = id;
		this.file_name = file_name;
	}

	public DealFile(String file_name) {
		super();
		this.file_name = file_name;
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	 
	@Column(name="ID")
	private String id;
	
	@Column(name="FILE_NAME")
	private String file_name;

	public DealFile() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	@Override
	public String toString() {
		return "DealFile [id=" + id + ", file_name=" + file_name + "]";
	}
	
	
	
	

}
