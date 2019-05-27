package com.example.render.dao.user;

import com.example.render.entity.user.Schema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserAccountRepository extends MongoRepository<Schema, String>, UserAccountInterface {

    public Schema findByEmail(String email);


}
