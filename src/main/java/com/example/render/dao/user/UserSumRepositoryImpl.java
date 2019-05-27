package com.example.render.dao.user;

import com.example.render.entity.user.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.example.render.entity.user.UserRelate;
import com.example.render.entity.user.UserSummed;

@Repository
public class UserSumRepositoryImpl implements UserSumInterface{

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@Override
	public void addUsers(Object id, Object user) {
		Query query = new Query(Criteria.where("refId").is(user));
		UserRelate ur = new UserRelate();
		ur.setUserId(id);
		ur.setRelation("stranger");
		Update update  = new Update().addToSet("summed", ur);
		mongoTemplate.updateFirst(query, update, UserSummed.class);
		
	}

    @Override
	public UserSummed getUserSums(String userId){

		return mongoTemplate.findOne(new Query(Criteria.where("refId").is(userId)), UserSummed.class);

	}
}
