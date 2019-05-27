package com.example.render.entity.user;

public class UserRelate {

	private Object userId;
	private String relation;
	
	
	public UserRelate() {
		super();
	}
	public UserRelate(Object userId, String relation) {
		super();
		this.userId = userId;
		this.relation = relation;
	}
	public Object getUserId() {
		return userId;
	}
	public void setUserId(Object userId) {
		this.userId = userId;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
}
