package com.example.render.dao.comparision;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.render.entity.comparision.ComparisionSchema;


@Repository
public interface ComparisionRepository extends MongoRepository<ComparisionSchema, String>, ComparisionInterface{

	
}
