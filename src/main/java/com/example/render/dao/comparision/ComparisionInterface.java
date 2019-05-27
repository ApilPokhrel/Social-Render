package com.example.render.dao.comparision;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.render.entity.comparision.ComparisionSchema;

public interface ComparisionInterface {

	List<ComparisionSchema> findAllUserComparision(Object refId);
	CompletableFuture<List<ComparisionSchema>> findAllUserComparisionAsync(Object refId);
	void addLikeToFile1(String compId, String userSlug);
	void addLikeToFile2(String compId, String userSlug);
	void addLikeToOpn(String compId, String userSlug, int opnIndex, String fieldName);
	void toogleOpnLike(String compId, String userSlug, int opnIndex, String fieldName);
	List<ComparisionSchema> getAllPost(int l, int s);

}
