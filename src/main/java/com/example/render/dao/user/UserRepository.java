package com.example.render.dao.user;

import com.example.render.entity.user.UserSchema;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<UserSchema, String>, UserRepositoryInterface{


}
