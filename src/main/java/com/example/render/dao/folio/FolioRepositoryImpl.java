package com.example.render.dao.folio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.example.render.entity.folio.FolioSchema;

import java.util.List;


@Repository()
public class FolioRepositoryImpl implements FolioInterface{

	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public FolioSchema findbyName(String name) {
		return mongoTemplate.findOne(new Query(Criteria.where("folioname").is(name)), FolioSchema.class);
	}
	
	
	@Override
	public FolioSchema findByRefId(Object id) {
		return mongoTemplate.findOne(new Query(Criteria.where("refuserId").is(id)), FolioSchema.class);

	}
	
	@Override
	public void addSubTags(String tagname, Object id) {
		Query query = new Query(Criteria.where("refuserId").is(id));
		Update update = new Update().addToSet("subtags", tagname);
		
		mongoTemplate.updateFirst(query, update, FolioSchema.class);
	}


	@Override
	public List<FolioSchema> userAllFolios(String userId){

		Query query = new Query(Criteria.where("refuserId").is(userId));

		return mongoTemplate.find(query, FolioSchema.class);
	}

	@Override
public FolioSchema findOneById(String id){
		return  mongoTemplate.findOne(new Query(Criteria.where("_id").is(id)), FolioSchema.class);

}
	
}
