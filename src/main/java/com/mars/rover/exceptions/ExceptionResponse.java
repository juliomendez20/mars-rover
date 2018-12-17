package com.mars.rover.exceptions;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timestamp;
	private String message;
	private Object object;
	private String detail;
	
	
	public ExceptionResponse(Date timestamp, Object object,String message, String detail) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.object = object;
		this.detail = detail;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
	
}
