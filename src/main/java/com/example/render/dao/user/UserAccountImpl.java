package com.example.render.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.render.entity.user.Schema;

@Repository
public class UserAccountImpl implements UserAccountInterface{

	@Autowired
	MongoTemplate mongoTemplate;
	
	
@Override
public Schema findOneBySlug(String slug) {
	return mongoTemplate.findOne(new Query(Criteria.where("slug").is(slug)), Schema.class);
}
	
}
