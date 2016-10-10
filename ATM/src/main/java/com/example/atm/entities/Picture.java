package com.example.atm.entities;

import java.io.Serializable;

public class Picture implements Serializable {

	private static final long serialVersionUID = 732546287867888715L;
	private String ContentType;
	private String Content;
	private String FileName;

	public String getContentType() {
		return ContentType;
	}

	public void setContentType(String contentType) {
		ContentType = contentType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public Picture(String contentType, String content, String fileName) {
		super();
		ContentType = contentType;
		Content = content;
		FileName = fileName;
	}

	public Picture() {
		super();
	}

}
