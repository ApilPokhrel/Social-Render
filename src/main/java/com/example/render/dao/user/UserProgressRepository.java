package com.example.render.dao.user;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.render.entity.user.UserAccountProgress;

@Repository
public interface UserProgressRepository extends MongoRepository<UserAccountProgress, String>, UserProgressInterface{
  
}
