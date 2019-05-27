package com.example.render.dao.folio;

import com.example.render.entity.folio.FolioSchema;

import java.util.List;

public interface FolioInterface {

	FolioSchema findbyName(String name);

	FolioSchema findByRefId(Object id);

	void addSubTags(String tagname, Object id);
	List<FolioSchema> userAllFolios(String userId);
	FolioSchema findOneById(String id);

}
