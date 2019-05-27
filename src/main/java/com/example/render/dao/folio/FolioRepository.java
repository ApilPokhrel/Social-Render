package com.example.render.dao.folio;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.render.entity.folio.FolioSchema;

@Repository()
public interface FolioRepository extends MongoRepository<FolioSchema, String>, FolioInterface{


}
