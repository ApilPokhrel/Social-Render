package com.example.render.entity.user;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usersums")
public class UserSummed {
    
	@Id
	private String id;
	private Object refId;
	private ArrayList<UserRelate> summed;
	private Object[] pageSummed;
	
	public UserSummed() {
		super();
	}
	
	public UserSummed(String id, Object refId, ArrayList<UserRelate> summed, Object[] pageSummed) {
		super();
		this.id = id;
		this.refId = refId;
		this.summed = summed;
		this.pageSummed = pageSummed;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getRefId() {
		return refId;
	}
	public void setRefId(Object refId) {
		this.refId = refId;
	}
	public ArrayList<UserRelate> getSummed() {
		return summed;
	}
	public void setSummed(ArrayList<UserRelate> summed) {
		this.summed = summed;
	}

	public Object[] getPageSummed() {
		return pageSummed;
	}

	public void setPageSummed(Object[] pageSummed) {
		this.pageSummed = pageSummed;
	}
	
}
