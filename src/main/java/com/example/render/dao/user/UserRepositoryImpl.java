package com.example.render.dao.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.render.entity.user.UserSchema;

@Repository
public abstract class UserRepositoryImpl implements UserRepositoryInterface{

	
	@Autowired
	MongoTemplate mongoTemplate;
	
//	@Override
//	@Async
//	public  CompletableFuture<UserSchema> findByRefidByAsync(Object id) {
//		Query query = new Query(Criteria.where("refId").is(id));
//		UserSchema result = mongoTemplate.findOne(query, UserSchema.class);
//        return CompletableFuture.completedFuture(result);
//
//	}
	
	@Override
	public UserSchema findByRefId(Object id) {
		Query query = new Query(Criteria.where("refId").is(id));
		UserSchema result = mongoTemplate.findOne(query, UserSchema.class);
		return result;
	}
}
