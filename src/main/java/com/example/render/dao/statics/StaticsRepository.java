package com.example.render.dao.statics;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.render.entity.statics.StaticsSchema;




@Repository()
public interface StaticsRepository extends MongoRepository<StaticsSchema, String>, StaticsInterface{

	
}
