package com.example.render.dao.user;

import java.util.concurrent.CompletableFuture;

import com.example.render.entity.user.UserAccountProgress;

public interface UserProgressInterface {

	UserAccountProgress findByRefId(Object id);

	CompletableFuture<UserAccountProgress> findByRefIdAsync(Object id);

}
