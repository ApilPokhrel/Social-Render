package com.example.render.dao.user;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Repository;

import com.example.render.entity.user.UserSchema;


@Repository
public interface UserRepositoryInterface {


	UserSchema findByRefId(Object id);


//	CompletableFuture<UserSchema> findByRefidByAsync(Object id);

}
