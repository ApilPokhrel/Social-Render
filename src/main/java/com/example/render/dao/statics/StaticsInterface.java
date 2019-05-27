package com.example.render.dao.statics;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.render.entity.statics.StaticsSchema;

public interface StaticsInterface {

	List<StaticsSchema> findAllUserStatics(Object refId);
	CompletableFuture<List<StaticsSchema>> findAllUserStaticsAsync(Object refId);
	void addLikeToFile(String statId, String userId);
	StaticsSchema toogleOpnLike(String compId, String userSlug, int opnIndex, String fieldName);
	List<StaticsSchema> getAllPost(int l, int s);
}
