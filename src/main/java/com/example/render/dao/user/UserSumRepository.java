package com.example.render.dao.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.render.entity.user.UserAccountProgress;
import com.example.render.entity.user.UserSummed;

@Repository
public interface UserSumRepository extends MongoRepository<UserSummed, String>, UserSumInterface{

	public void addUsers(Object id, Object user);

}
