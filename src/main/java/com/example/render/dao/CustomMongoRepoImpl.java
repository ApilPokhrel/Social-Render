package com.example.render.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.render.entity.user.Schema;
import com.example.render.entity.Tokens;
import com.example.render.entity.user.UserAccountProgress;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Repository
public class CustomMongoRepoImpl implements CustomMongoRepo{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Schema findByToken(String token) {
		Object id = null;
		try {
		Jws<Claims> claims = Jwts.parser()
				.setSigningKey("mysecrethere...".getBytes())
				.parseClaimsJws(token);
				
		id = claims.getBody().get("_id");
		} catch(Exception ex) {}
		
		Schema user = null;
		try {
			new Criteria();
		user = mongoTemplate.findOne(new Query( Criteria.where("_id").is(id)).addCriteria( Criteria.where("tokens.token").is(token)), Schema.class);
		}catch(Exception ex) {}
		
		return user;
	}
	
	@Override
	public void removeToken(String email, String token) {
		Tokens tokens = new Tokens();
		tokens.setAccess("auth");
		tokens.setToken(token);
		Query query = new Query(Criteria.where("email").is(email));
		Update update = new Update().pull("tokens", tokens);
		mongoTemplate.updateFirst(query, update, Schema.class);
	}
	
	@Override
	public UserAccountProgress findByRefId(Object id) {
		
		Query query = new Query(Criteria.where("refId").is(id));
		return mongoTemplate.findOne(query, UserAccountProgress.class);
		
		
	}
	
	@Override
	public void updateUserImg(String filename, Object id) {
		Query query = new Query(Criteria.where("_id").is(id));
		new Update();
		Update update = Update.update("image", filename);
		mongoTemplate.updateFirst(query, update, Schema.class);
	}


	@Override
	public void findByIdAndRemove(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, Schema.class);
	}
	
	
}
