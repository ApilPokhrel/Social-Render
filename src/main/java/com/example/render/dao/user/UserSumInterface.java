package com.example.render.dao.user;

import com.example.render.entity.user.UserSummed;

public interface UserSumInterface {

	public void addUsers(Object id, Object user);
	public UserSummed getUserSums(String userId);

}
