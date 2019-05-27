package com.example.render.dao.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.example.render.entity.user.UserAccountProgress;

@Repository
public class UserProgressRepositoryImpl implements UserProgressInterface{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	@Async
	public  CompletableFuture<UserAccountProgress> findByRefIdAsync(Object id) {
		Query query = new Query(Criteria.where("refId").is(id));
		UserAccountProgress result = mongoTemplate.findOne(query, UserAccountProgress.class);
        return CompletableFuture.completedFuture(result);

	}
	
	@Override
	public  UserAccountProgress findByRefId(Object id) {
		Query query = new Query(Criteria.where("refId").is(id));
		UserAccountProgress result = mongoTemplate.findOne(query, UserAccountProgress.class);
        return result;

	}
}
