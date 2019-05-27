package com.example.render.dao.comparision;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.example.render.entity.comparision.ComparisionSchema;


@Repository
public  class ComparisionRepositoryImpl implements ComparisionInterface{

	@Autowired
	MongoTemplate mongoTemplate;


	@Override
	@Async
	public CompletableFuture<List<ComparisionSchema>> findAllUserComparisionAsync(Object refId){
		
		Query query = new Query(Criteria.where("refId").is(refId));
		
		List<ComparisionSchema> list = mongoTemplate.find(query, ComparisionSchema.class);
		
		return CompletableFuture.completedFuture(list);
	}

	@Override
	public List<ComparisionSchema> findAllUserComparision(Object refId){

		Query query = new Query(Criteria.where("refId").is(refId));

		List<ComparisionSchema> list = mongoTemplate.find(query, ComparisionSchema.class);

		return list;
	}


	@Override
	public void addLikeToFile1(String compId, String userSlug){

		System.out.println("in like 1");

     System.out.println("id is " +compId);


		if(checkUserLike("f1likes",compId, userSlug) == null) {


			if(checkUserLike("f2likes", compId, userSlug)== null) {
				addToField("f1likes", compId, userSlug);

			} else{
				pullFromField("f2likes", compId, userSlug);
				addToField("f1likes", compId, userSlug);

			}

		}  else{

			pullFromField("f1likes", compId, userSlug);
		}

	}


	@Override
	public void addLikeToFile2(String compId, String userSlug){

		if(checkUserLike("f2likes",compId, userSlug) == null) {

			if(checkUserLike("f1likes", compId, userSlug) == null){
				addToField("f2likes", compId, userSlug);

			}
			else {

				pullFromField("f1likes", compId, userSlug);
				addToField("f2likes", compId, userSlug);
			}

		} else{

			pullFromField("f2likes", compId, userSlug);
		}
	}




	public ComparisionSchema checkUserLike(String fieldname, String compId, String userSlug){

		ComparisionSchema comparisionSchema = null;
		try {
			comparisionSchema = mongoTemplate.findOne(new Query(Criteria.where("_id").is(compId)).addCriteria(Criteria.where(fieldname).is(userSlug)), ComparisionSchema.class);
		} catch(Exception ex){System.out.println("error checking user  "+ ex);}

         return comparisionSchema;
	}




	public void addToField(String fieldname, String compId, String userSlug){
		System.out.println("in add to set");
		Query query = new Query(Criteria.where("_id").is(compId));
		Update update = new Update().addToSet(fieldname, userSlug);
		mongoTemplate.updateFirst(query, update, ComparisionSchema.class);
	}


	public void pullFromField(String fieldname, String compId, String userSlug){
		Query query = new Query(Criteria.where("_id").is(compId));
		Update update = new Update().pull(fieldname, userSlug);
		mongoTemplate.updateFirst(query, update, ComparisionSchema.class);
	}


	@Override
	public void addLikeToOpn(String compId, String userSlug, int opnIndex, String fieldName){

		Query query = new Query(Criteria.where("_id").is(compId));
		Update update = new Update().addToSet("opinions."+opnIndex+"."+fieldName,userSlug);
		mongoTemplate.updateFirst(query, update, ComparisionSchema.class);


	}


	public void pullFromOPn(String compId, String userSlug, int opnIndex, String fieldName){
		Query query = new Query(Criteria.where("_id").is(compId));
		Update update = new Update().pull("opinions."+opnIndex+"."+fieldName,userSlug);
		mongoTemplate.updateFirst(query, update, ComparisionSchema.class);

	}


	@Override
	public void toogleOpnLike(String compId, String userSlug, int opnIndex, String fieldName){


		if(checkOpinionLike(compId, userSlug, opnIndex, fieldName) == null){

			addLikeToOpn(compId, userSlug, opnIndex, fieldName);
		} else{

			pullFromOPn(compId, userSlug, opnIndex, fieldName);

		}


	}


	public ComparisionSchema checkOpinionLike(String compId, String userSlug, int opnIndex, String fieldName){
		ComparisionSchema comparisionSchema = null;
		try{
			comparisionSchema = mongoTemplate.findOne(new Query(Criteria.where("_id").is(compId)).addCriteria(Criteria.where("opinions."+opnIndex+"."+fieldName).is(userSlug)), ComparisionSchema.class);
		}catch(Exception ex){}

		return comparisionSchema;
	}




	//getALlComparing with limit
     @Override
	public  List<ComparisionSchema> getAllPost(int l, int s){

		Query query = new Query().with(new Sort(Sort.Direction.DESC, "_id")).skip(s).limit(l);

		return mongoTemplate.find(query, ComparisionSchema.class);



	};
}
