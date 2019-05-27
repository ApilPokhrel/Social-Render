package com.example.render.dao.statics;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.example.render.entity.statics.StaticsSchema;


@Repository
public  class StaticsRepositoryImpl implements StaticsInterface{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	@Async
	public CompletableFuture<List<StaticsSchema>> findAllUserStaticsAsync(Object refId){
		
		Query query = new Query(Criteria.where("refId").is(refId));
		
		List<StaticsSchema> list = mongoTemplate.find(query, StaticsSchema.class);
		
        return CompletableFuture.completedFuture(list);

	}

	@Override
	public List<StaticsSchema> findAllUserStatics(Object refId){

		Query query = new Query(Criteria.where("refId").is(refId));

		List<StaticsSchema> list = mongoTemplate.find(query, StaticsSchema.class);

		return list;

	}


	@Override
	public void addLikeToFile(String statId, String userSlug){

		if(checkUserLike("filelikes",statId, userSlug) == null) {


				addToField("filelikes", statId, userSlug);


		} else{

			pullFromField("filelikes", statId, userSlug);
		}


	}



	public StaticsSchema checkUserLike(String fieldname, String statId, String userSlug){

		StaticsSchema staticsSchema = null;
		try {
			staticsSchema = mongoTemplate.findOne(new Query(Criteria.where("_id").is(statId)).addCriteria(Criteria.where(fieldname).is(userSlug)), StaticsSchema.class);
		} catch(Exception ex){System.out.println("error checking user  "+ ex);}

		return staticsSchema;
	}


	public void addToField(String fieldname, String statId, String userSlug){
		System.out.println("in add to set");
		Query query = new Query(Criteria.where("_id").is(statId));
		Update update = new Update().addToSet(fieldname, userSlug);
		mongoTemplate.updateFirst(query, update, StaticsSchema.class);
	}


	public void pullFromField(String fieldname, String statId, String userSlug){
		Query query = new Query(Criteria.where("_id").is(statId));
		Update update = new Update().pull(fieldname, userSlug);
		mongoTemplate.updateFirst(query, update, StaticsSchema.class);
	}




	public StaticsSchema addLikeToOpn(String compId, String userSlug, int opnIndex, String fieldName){

		Query query = new Query(Criteria.where("_id").is(compId));
		Update update = new Update().addToSet("opinions."+opnIndex+"."+fieldName,userSlug);
		return mongoTemplate.findAndModify(query, update,new FindAndModifyOptions().returnNew(true), StaticsSchema.class);


	}


	public StaticsSchema pullFromOPn(String compId, String userSlug, int opnIndex, String fieldName){
		Query query = new Query(Criteria.where("_id").is(compId));
		Update update = new Update().pull("opinions."+opnIndex+"."+fieldName,userSlug);
		return mongoTemplate.findAndModify(query, update,new FindAndModifyOptions().returnNew(true), StaticsSchema.class);

	}


	@Override
	public StaticsSchema toogleOpnLike(String compId, String userSlug, int opnIndex, String fieldName){

   StaticsSchema sc;
		if(checkOpinionLike(compId, userSlug, opnIndex, fieldName) == null){

			sc = addLikeToOpn(compId, userSlug, opnIndex, fieldName);
		} else{

			sc = pullFromOPn(compId, userSlug, opnIndex, fieldName);

		}

      return sc;
	}


	public StaticsSchema checkOpinionLike(String compId, String userSlug, int opnIndex, String fieldName){
		StaticsSchema staticsSchema = null;
		try{
			staticsSchema = mongoTemplate.findOne(new Query(Criteria.where("_id").is(compId)).addCriteria(Criteria.where("opinions."+opnIndex+"."+fieldName).is(userSlug)), StaticsSchema.class);
		}catch(Exception ex){}

		return staticsSchema;
	}



	//getALlComparing with limit
	@Override
	public  List<StaticsSchema> getAllPost(int l, int s){


		Query query = new Query().with(new Sort(Sort.Direction.DESC, "_id")).skip(s).limit(l);

		return mongoTemplate.find(query, StaticsSchema.class);



	};
}
