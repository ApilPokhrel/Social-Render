package com.example.render.entity;

public class Devoted {

	private String reason;
	private Object refUser;
	
	
	
	public Devoted(String reason, Object refUser) {
		super();
		this.reason = reason;
		this.refUser = refUser;
	}
	
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Object getRefUser() {
		return refUser;
	}
	public void setRefUser(Object refUser) {
		this.refUser = refUser;
	}
}
