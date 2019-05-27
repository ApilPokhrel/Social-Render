package com.example.render.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.render.entity.user.Schema;
import com.example.render.entity.user.UserAccountProgress;

@Repository
public interface CustomMongoRepo {

	
	public Schema findByToken(String token);
	
	public void removeToken(String email, String token);

	public UserAccountProgress findByRefId(Object id);

	public void updateUserImg(String filename, Object id);

	public void findByIdAndRemove(String id);
}
